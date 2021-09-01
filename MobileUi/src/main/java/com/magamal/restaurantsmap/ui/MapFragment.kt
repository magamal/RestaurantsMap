package com.magamal.restaurantsmap.ui

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.preference.PreferenceManager
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.magamal.common.GlobalConstants
import com.magamal.common.debug
import com.magamal.restaurantsmap.BuildConfig
import com.magamal.restaurantsmap.R
import com.magamal.restaurantsmap.base.BaseFragment
import com.magamal.restaurantsmap.extentions.loadImage
import com.magamal.restaurantsmap.extentions.toVisible
import com.magamal.restaurantsmap.utils.AppLocationManager
import com.magamal.restaurantsmap.utils.PermissionsHelper
import com.restaurantsmap.presentation.models.restaurants.RestaurantView
import com.restaurantsmap.presentation.viewmodels.RestaurantsViewModel
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.item_restaurant.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import toast


/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class MapFragment : BaseFragment(), AppLocationManager.Callback {


    private var dialog: AlertDialog? = null
    private val permissionsHelper by lazy { PermissionsHelper(activity as AppCompatActivity) }
    private lateinit var appLocationManager: AppLocationManager
    private val viewModel: RestaurantsViewModel by sharedViewModel()

    override fun getLayoutRes(): Int = R.layout.fragment_map

    override fun setupData() {
        dialog = null
        Configuration.getInstance()
            .load(context, PreferenceManager.getDefaultSharedPreferences(context))
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        appLocationManager = AppLocationManager(activity as AppCompatActivity, this)
    }

    override fun setupViews() {
        if (isWriteStoragePermissionEnabled()) {
            initMapFragment()
        } else {
            requestStoragePermissions()
        }
    }

    override fun setupObservers() {
        viewModel.observeOnSelectedRestaurants(
            owner = this,
            onLoading = {
                debug("observeOnSelectedRestaurants: onLoading")
            }, onError = {
                debug("observeOnSelectedRestaurants: onError")
            },
            onNewData = { restaurant ->
                debug("observeOnSelectedRestaurants: onNewData")
                restaurant.location?.lat?.let { lat ->
                    restaurant.location?.lng?.let { lng ->
                        zoomToPoint(lat, lng)
                        restaurantView.toVisible()
                        updateDataOfRestaurantItem(restaurant)
                    }
                }
            }
        )
    }

    private fun updateDataOfRestaurantItem(restaurant: RestaurantView) {
        restaurantView.apply {
            restaurant.apply {
                tvTitle.text = name
                tvLocation.text =
                    String.format(
                        getString(R.string.location_format),
                        location?.address ?: "",
                        location?.crossStreet ?: "",
                        location?.city ?: ""
                    )
                image.loadImage(photos?.first())
            }
        }
    }

    fun refreshData() {
        if (!isWriteStoragePermissionEnabled()) {
            requestStoragePermissions()
        } else if (!isDataSetuped()) {
            initMapFragment()
        } else {
            appLocationManager.setupLocationListener()
        }
    }

    private fun isDataSetuped(): Boolean = appLocationManager != null

    private fun initMapFragment() {
        setupData()
        setupMap()
        setupLocationManager()
    }

    private fun setupLocationManager() {
        appLocationManager.setupLocationListener()
    }

    override fun onLocationDisabled() {
        showAlertOpenLocation()
    }

    override fun onNetworkDisabled() {
        showAlertOpenNetwork()
    }

    override fun onGettingLocation(latitude: Double, longitude: Double) {
        debug("onGettingLocation lat:$latitude lng:$longitude")
        viewModel.clearToNewFetchLocation(latitude, longitude)
    }

    override fun onGettingLocationError() {
        toast(R.string.location_error)
    }

    private fun showAlertOpenNetwork() {
        context?.let { context ->
            dialog = AlertDialog.Builder(context)
                .setTitle(R.string.error_open_network)
                .setPositiveButton(R.string.open) { _: DialogInterface, _: Int ->
                    openWifiSettings()
                }.setCancelable(true)
                .show()
        }

    }

    private fun openWifiSettings() {
        startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }

    private fun openLocationSettings() {
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }

    private fun showAlertOpenLocation() {
        context?.let { context ->
            dialog = AlertDialog.Builder(context)
                .setTitle(R.string.error_open_location)
                .setPositiveButton(R.string.open) { _: DialogInterface, _: Int ->
                    openLocationSettings()
                }.setCancelable(true)
                .show()
        }
    }

    private fun setupMap() {
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.zoomController?.setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        mapView.setMultiTouchControls(true);
        zoomToPoint(GlobalConstants.CAIRO_LAT, GlobalConstants.CAIRO_LONG)
    }


    private fun zoomToPoint(latitude: Double, longitude: Double) {
        val mapController = mapView.controller
        mapController?.setZoom(DEFAULT_CAMERA_ZOOM)
        val startPoint = GeoPoint(latitude, longitude)
        mapController?.setCenter(startPoint)
    }


    private fun requestStoragePermissions() =
        permissionsHelper.requestStoragePermissions(STORAGE_PERMISSION_REQUEST_CODE)


    private fun isWriteStoragePermissionEnabled(): Boolean =
        permissionsHelper.isWriteStoragePermissionGranted()

    private fun isLocationPermissionEnabled(): Boolean =
        permissionsHelper.isLocationPermissionGranted()

    override fun onPause() {
        dialog?.dismiss()
        super.onPause()
    }

    override fun onDestroy() {
        appLocationManager.destroy()
        super.onDestroy()
    }

    fun handlePermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            handlePermissionResultForStorage()
        } else {
            handlePermissionResultForLocation()
        }
    }

    private fun handlePermissionResultForLocation() {
        if (isLocationPermissionEnabled()) {
            appLocationManager.setupLocationListener()
        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            showAlertPermissionIsDenied()
        } else {
            toast(R.string.permission_denied_msg)
        }
    }

    private fun handlePermissionResultForStorage() {
        if (isWriteStoragePermissionEnabled()) {
            initMapFragment()
        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showAlertPermissionIsDenied()
        } else {
            toast(R.string.permission_denied_msg)
        }
    }

    private fun showAlertPermissionIsDenied() {
        context?.let { context ->
            dialog = AlertDialog.Builder(context)
                .setTitle(R.string.permission_denied)
                .setMessage(R.string.permission_denied_msg)
                .setPositiveButton(android.R.string.ok) { dialog: DialogInterface, _: Int ->
                    openPermissionsSettings()
                    dialog.dismiss()
                }.setCancelable(false)
                .show()
        }
    }

    private fun openPermissionsSettings() {
        startActivity(Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", activity?.applicationContext?.packageName, null)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    companion object {
        const val STORAGE_PERMISSION_REQUEST_CODE = 1
        private const val DEFAULT_CAMERA_ZOOM = 19.5
    }
}
package com.magamal.restaurantsmap.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationServices
import com.magamal.restaurantsmap.extentions.isConnectedToNetwork
import com.magamal.restaurantsmap.ui.MapFragment

/**
 * @author Mahmoud Gamal on 23/08/2021.
 */
private const val LOCATION_PERMISSION_REQUEST_CODE = 22

class AppLocationManager(
    private val activity: AppCompatActivity,
    private val callback: Callback
) : LocationListener {

    private val permissionHelper = PermissionsHelper(activity)
    private var locationManager: LocationManager? = null


    init {
        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    }

    fun setupLocationListener() {
        if (!isLocationPermissionEnabled()) {
            permissionHelper.requestLocationPermissions(LOCATION_PERMISSION_REQUEST_CODE)
        } else if (!isLocationEnabled()) {
            callback.onLocationDisabled()
        } else if (!activity.isConnectedToNetwork()) {
            callback.onNetworkDisabled()
        } else {
            getCurrentLocation()
        }
    }

    private fun getCurrentLocation() {
        if (isGooglePlayServicesAvailable()) {
            getLocationWithPlay()
        } else {
            getLocationWithGPS()
        }
    }


    private fun isGooglePlayServicesAvailable(): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(activity)
        if (status != ConnectionResult.SUCCESS) {
            return false
        }
        return true
    }


    @SuppressLint("MissingPermission")
    private fun getLocationWithPlay() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        fusedLocationClient
            .lastLocation
            ?.addOnSuccessListener { location ->
                if (location == null) {
                    getLocationWithGPS()
                } else {
                    callback.onGettingLocation(location.latitude, location.longitude)
                }
            }
    }

    @SuppressLint("MissingPermission")
    private fun getLocationWithGPS() {
        val location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        locationManager?.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 1000.toLong(), 10f, this
        )
    }


    private fun isLocationEnabled(): Boolean =
        locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true


    private fun isLocationPermissionEnabled(): Boolean =
        permissionHelper.isLocationPermissionGranted()


    override fun onLocationChanged(location: Location?) {
        if (location == null) {
            callback.onGettingLocationError()
            return
        }
        callback.onGettingLocation(location.latitude, location.longitude)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {}

    fun destroy() {
        try {
            locationManager?.removeUpdates(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface Callback {
        fun onLocationDisabled()
        fun onNetworkDisabled()
        fun onGettingLocation(lat: Double, lng: Double)
        fun onGettingLocationError()
    }

}
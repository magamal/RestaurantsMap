package com.magamal.restaurantsmap.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.magamal.restaurantsmap.R

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var restaurantsFragment: RestaurantsFragment
    private lateinit var mapFragment: MapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        restaurantsFragment = RestaurantsFragment()
        mapFragment = MapFragment()

        addFragment(restaurantsFragment, R.id.fragmentListContainer)
        addFragment(mapFragment, R.id.fragmentMapContainer)
    }

    private fun addFragment(fragment: Fragment, resId: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(resId, fragment)
            .commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mapFragment.handlePermissionResult(requestCode, permissions, grantResults)
    }

    override fun onStart() {
        super.onStart()
        mapFragment.refreshData()

    }
}
package com.magamal.restaurantsmap.utils;

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by Mahmoud Gamal on 2/26/19.
 */

class PermissionsHelper(private val activity: AppCompatActivity) {
    companion object {

        private const val WRITE_STORAGE_PERMISSION =
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE

        private const val ACCESS_FINE_LOCATION_PERMISSION =
            android.Manifest.permission.ACCESS_FINE_LOCATION

        val STORAGE_PERMISSIONS = arrayOf(WRITE_STORAGE_PERMISSION)
    }


    fun isWriteStoragePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            activity,
            WRITE_STORAGE_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestStoragePermissions(requestCode: Int) {
        ActivityCompat.requestPermissions(activity, STORAGE_PERMISSIONS, requestCode);
    }


    fun canUserAskForPermissions(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission))
                return false
        }
        return true
    }

    fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            activity,
            ACCESS_FINE_LOCATION_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermissions(requestCode: Int) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(ACCESS_FINE_LOCATION_PERMISSION), requestCode
        );
    }


    fun arePermissionsGranted(grantResults: IntArray?): Boolean {
        if (grantResults != null && grantResults.isNotEmpty()) {
            for (result in grantResults) {
                if (result == PackageManager.PERMISSION_DENIED)
                    return false
            }
            return true
        }
        return false
    }
}
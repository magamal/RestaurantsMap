package com.magamal.restaurantsmap.extentions

import android.content.Context
import android.net.ConnectivityManager

/**
 * @author Mahmoud Gamal on 21/08/2021.
 */

inline fun <reified C : Context?> C.isConnectedToNetwork() = this?.run {
    (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnected
} ?: false
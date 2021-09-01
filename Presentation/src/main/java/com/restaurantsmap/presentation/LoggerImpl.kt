package com.restaurantsmap.presentation

import android.util.Log
import com.magamal.common.Logger

/**
 * @author Mahmoud Gamal on 20/08/2021.
 */
class LoggerImpl() : Logger {

    override fun debug(className: String, message: String) {
        Log.d(className, message)
    }

    override fun error(className: String, message: String) {
        Log.e(className, message)
    }

    override fun error(className: String, message: String, tr: Throwable) {
        Log.e(className, message, tr)
    }

}
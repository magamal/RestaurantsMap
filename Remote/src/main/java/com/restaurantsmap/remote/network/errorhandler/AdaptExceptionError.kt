package com.restaurantsmap.remote.network.errorhandler

import com.magamal.common.AppException
import com.restaurantsmap.remote.models.GenericResponse
import com.restaurantsmap.remote.models.RemoteModel

/**
 * Created by Mahmoud Gamal on 4/11/19.
 */

private fun checkIsErrorFound(response: Any) {
    if (response is GenericResponse<*>) {
        if (response.meta.errorDetail?.isNullOrEmpty() == false) {
            throw AppException(
                exceptionType = AppException.ExceptionType.SERVER_FAILED,
                throwable = Throwable(),
                errorMsg = response.meta.errorDetail
            )
        }
    }
}

fun <T : RemoteModel> T.adaptExceptionError(): T {
    try {
        checkIsErrorFound(this)
    } catch (e: Exception) {
        e.printStackTrace()
        throw  ExceptionHandler.adapt(e)
    }
    return this
}

package com.magamal.common

class AppException(
    val exceptionType: ExceptionType,
    throwable: Throwable,
    val errorMsg: String?
) : Exception(throwable) {

    enum class ExceptionType {
        UNKNOWN,
        UNAUTHORIZED,
        NETWORK,
        SERVER_FAILED,
        PARSING
    }

}
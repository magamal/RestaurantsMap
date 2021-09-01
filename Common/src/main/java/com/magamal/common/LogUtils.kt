package com.magamal.common

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * @author Mahmoud Gamal on 20/08/2021.
 */

private object LoggerObj : KoinComponent {
    val logger by inject<Logger>()
}

fun Any.debug(message: String) {
    LoggerObj.logger.debug(this.javaClass.simpleName, message)
}

fun Any.error(message: String) {
    LoggerObj.logger.error(this.javaClass.simpleName, message)
}

fun Any.error(message: String, tr: Throwable) {
    LoggerObj.logger.error(this.javaClass.simpleName, message, tr)
}



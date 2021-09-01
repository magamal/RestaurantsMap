package com.magamal.common

/**
 * @author Mahmoud Gamal on 20/08/2021.
 */
interface Logger {
    fun debug(className: String, message: String)

    fun error(className: String, message: String)

    fun error(className: String, message: String, tr: Throwable)
}
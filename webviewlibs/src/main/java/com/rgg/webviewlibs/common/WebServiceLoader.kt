package com.rgg.webviewlibs.common

import java.util.*

object WebServiceLoader {

    fun <T> load(service: Class<T>?): T? {
        return try {
            ServiceLoader.load(service).iterator().next()
        } catch (e: Exception) {
            null
        }
    }
}
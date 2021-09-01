package com.restaurantsmap.remote.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(
                RestApiConstants.CLIENT_ID_PARAMS_KEY,
                RestApiConstants.CLIENT_ID_PARAMS_VALUE
            )
            .addQueryParameter(
                RestApiConstants.CLIENT_SECRET_KEY,
                RestApiConstants.CLIENT_SECRET_VALUE
            )
            .addQueryParameter(RestApiConstants.V_PARAMS_KEY, RestApiConstants.V_PARAMS_VALUE)
            .build()

        val requestBuilder = original.newBuilder().url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
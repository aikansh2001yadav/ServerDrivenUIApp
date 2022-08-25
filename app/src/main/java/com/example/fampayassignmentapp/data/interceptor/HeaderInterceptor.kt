package com.example.fampayassignmentapp.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response


/**
 * Helper Interceptor class for setting of the headers for every request
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder().apply {
            addHeader("Accept", "application/json")
        }.build()

        return chain.proceed(request)
    }
}

package com.example.kinoapp.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val request = origin.newBuilder()
            .addHeader("Authorization", "")
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept-Language", "")
            .build()

        return chain.proceed(request)
    }
}
package com.tadeujeronimo.deliveryapp.data.util

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = if (!SharedPreferences.token.isNullOrEmpty()) {
            request.newBuilder()
                .header("Authorization", "Bearer ${SharedPreferences.token}")
                .build()
        } else {
            request.newBuilder()
                .build()
        }

        return chain.proceed(request)
    }
}
package com.gka.myflickclient.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        request = request.newBuilder()
                .addHeader("Content-kitchens", "application/json")
                .build()
        return chain.proceed(request)
    }
}
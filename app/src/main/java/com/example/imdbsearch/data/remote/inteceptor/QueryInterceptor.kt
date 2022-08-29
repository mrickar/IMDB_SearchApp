package com.example.imdbsearch.data.remote.inteceptor

import com.example.imdbsearch.common.Constants
import okhttp3.Interceptor
import okhttp3.Response

class QueryInterceptor:okhttp3.Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest=chain.request()
        val originalUrl=originalRequest.url
        val newUrl=originalUrl.newBuilder()
            .addQueryParameter("apikey", Constants.API_KEY)
            .build()
        val newRequest= originalRequest.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}
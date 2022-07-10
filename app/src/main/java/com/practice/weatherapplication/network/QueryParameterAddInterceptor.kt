package com.practice.weatherapplication.network

import com.practice.weatherapplication.BuildConfig
import com.practice.weatherapplication.network.api.ServicePARAMS
import okhttp3.Interceptor
import okhttp3.Response

class QueryParameterAddInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request().url.newBuilder()
            .addQueryParameter(ServicePARAMS.APP_ID, BuildConfig.APP_ID)
            .build()

        val request = chain.request().newBuilder()
            // .addHeader("Authorization", "Bearer token")
            .url(url)
            .build()

        return chain.proceed(request)
    }
}

package com.practice.weatherapplication.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object HttpClient {

    private var client: OkHttpClient? = null

    val httpClient: OkHttpClient
        get() {
            if (client == null) {
                synchronized(HttpClient::class.java) {
                    if (client == null) {

                        val loggingInterceptor = HttpLoggingInterceptor()
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                        val httpClient = OkHttpClient.Builder()
                            .addInterceptor(QueryParameterAddInterceptor())

                        // for pretty log of HTTP request-response
                        httpClient.addInterceptor(loggingInterceptor)

                        client = httpClient.build()
                    }
                }
            }
            return client!!
        }
}

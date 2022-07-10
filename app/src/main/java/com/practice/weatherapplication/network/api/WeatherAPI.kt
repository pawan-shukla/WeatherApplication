package com.practice.weatherapplication.network.api

import com.practice.weatherapplication.network.api.ServiceName.WEATHER_API
import com.practice.weatherapplication.network.response.WeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET(WEATHER_API)
    fun getWeatherInfoById(@Query("id") cityId: Int): Single<WeatherResponse>

    @GET(WEATHER_API)
    fun getWeatherInfoByName(@Query("q") cityName: String): Single<WeatherResponse>
}

internal object ServiceName {
    const val WEATHER_API = "weather"
}

internal object ServicePARAMS {
    const val APP_ID = "appid"
}
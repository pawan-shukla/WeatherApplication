package com.practice.weatherapplication.network.service

import com.practice.weatherapplication.network.RetrofitManager
import com.practice.weatherapplication.network.api.WeatherAPI

class WeatherService {

    fun getWeatherAPI(): WeatherAPI {
        return RetrofitManager.client.create(WeatherAPI::class.java)
    }
}
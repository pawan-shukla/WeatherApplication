package com.practice.weatherapplication.network.api

import com.practice.weatherapplication.network.RetrofitManager

class APIManager {

    fun getWeather(): WeatherAPI {
        return RetrofitManager.client.create(WeatherAPI::class.java)
    }
}
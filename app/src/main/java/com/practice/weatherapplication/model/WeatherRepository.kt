package com.practice.weatherapplication.model

import com.practice.weatherapplication.network.response.WeatherResponse
import com.practice.weatherapplication.network.service.WeatherService
import io.reactivex.rxjava3.core.Single

/**
 * This class will be responsible to fetch the data from multiple data source like db, web, cache
 * This will achieve the
 */
class WeatherRepository{

    private val weatherService = WeatherService()

    fun getWeather(cityId: Int): Single<WeatherResponse> {
        return geWeatherDataById(cityId)
    }

    fun getWeather(cityName: String): Single<WeatherResponse> {
        return geWeatherDataByName(cityName)
    }

    private fun geWeatherDataById(cityId: Int): Single<WeatherResponse> {
        return weatherService.getWeatherAPI().getWeatherInfoById(cityId)
    }

    private fun geWeatherDataByName(cityName: String): Single<WeatherResponse> {
        return weatherService.getWeatherAPI().getWeatherInfoByName(cityName)
    }
}
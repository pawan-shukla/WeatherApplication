package com.practice.weatherapplication.network.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coord: Coord = Coord(),
    @SerializedName("weather")
    val weather: List<Weather> = listOf(),
    @SerializedName("base")
    val base: String = "",
    @SerializedName("main")
    val main: Main = Main(),
    @SerializedName("visibility")
    val visibility: Int = 0,
    @SerializedName("wind")
    val wind: Wind = Wind(),
    @SerializedName("clouds")
    val clouds: Clouds = Clouds(),
    @SerializedName("dt")
    val dt: Int = 0,
    @SerializedName("sys")
    val sys: Sys = Sys(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("cod")
    val cod: Int = 0
)

data class Coord(
    @SerializedName("lon")
    val lon: Double = 0.0,
    @SerializedName("lat")
    val lat: Double = 0.0
)

data class Weather(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("main")
    val main: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("icon")
    val icon: String = ""
)

data class Main(
    @SerializedName("temp")
    val temp: Double = 0.0,
    @SerializedName("pressure")
    val pressure: Double = 0.0,
    @SerializedName("humidity")
    val humidity: Int = 0,
    @SerializedName("temp_min")
    val tempMin: Double = 0.0,
    @SerializedName("temp_max")
    val tempMax: Double = 0.0
)

data class Wind(
    @SerializedName("speed")
    val speed: Double = 0.0,
    @SerializedName("deg")
    val deg: Double = 0.0
)


data class Clouds(
    @SerializedName("all")
    val all: Int = 0
)

data class Sys(
    @SerializedName("type")
    val type: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("message")
    val message: Double = 0.0,
    @SerializedName("country")
    val country: String = "",
    @SerializedName("sunrise")
    val sunrise: Int = 0,
    @SerializedName("sunset")
    val sunset: Int = 0
)
package com.practice.weatherapplication.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.weatherapplication.model.WeatherData
import com.practice.weatherapplication.model.WeatherRepository
import com.practice.weatherapplication.util.kelvinToCelsius
import com.practice.weatherapplication.util.unixTimestampToDateTimeString
import com.practice.weatherapplication.util.unixTimestampToTimeString
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class WeatherVm : ViewModel() {

    private val TAG = "WeatherVm"

    private val _weatherData = MutableLiveData<WeatherData>()
    val weatherData :LiveData<WeatherData> = _weatherData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error : LiveData<String?> = _error

    private val weatherManager = WeatherRepository()

    fun fetchWeatherById(cityId: Int) {
        _loading.value  = true
        weatherManager.getWeather(cityId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    //Here we update the VM property only which will be used by the view if it is ready
                    // business logic and data manipulation tasks should be done here
                    val wData = WeatherData(
                        dateTime = it.dt.unixTimestampToDateTimeString(),
                        temperature = it.main.temp.kelvinToCelsius().toString(),
                        cityAndCountry = "${it.name}, ${it.sys.country}",
                        weatherConditionIconUrl = "http://openweathermap.org/img/w/${it.weather[0].icon}.png",
                        weatherConditionIconDescription = it.weather[0].description,
                        humidity = "${it.main.humidity}%",
                        pressure = "${it.main.pressure} mBar",
                        visibility = "${it.visibility/1000.0} KM",
                        sunrise = it.sys.sunrise.unixTimestampToTimeString(),
                        sunset = it.sys.sunset.unixTimestampToTimeString()
                    )
                    _weatherData.value = wData
                    _error.value = null
                    _loading.value = false
                }
            }, {
                Timber.tag(TAG).e(it.localizedMessage)
                //update  view  for error
                onError(it.localizedMessage)
            })
    }

    fun fetchWeatherByName(cityName: String) {
        _loading.value  = true
        weatherManager.getWeather(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    //Here we update the VM property only which will be used by the view if it is ready
                    // business logic and data manipulation tasks should be done here
                    val wData = WeatherData(
                        dateTime = it.dt.unixTimestampToDateTimeString(),
                        temperature = it.main.temp.kelvinToCelsius().toString(),
                        cityAndCountry = "${it.name}, ${it.sys.country}",
                        weatherConditionIconUrl = "http://openweathermap.org/img/w/${it.weather[0].icon}.png",
                        weatherConditionIconDescription = it.weather[0].description,
                        humidity = "${it.main.humidity}%",
                        pressure = "${it.main.pressure} mBar",
                        visibility = "${it.visibility/1000.0} KM",
                        sunrise = it.sys.sunrise.unixTimestampToTimeString(),
                        sunset = it.sys.sunset.unixTimestampToTimeString()
                    )
                    _weatherData.value = wData
                    _error.value = null
                    _loading.value = false
                }
            }, {
                Timber.tag(TAG).e(it.localizedMessage)
                //update  view  for error
                onError(it.localizedMessage)
            })
    }

    private fun onError(message: String){
        _error.value = message
        _loading.value = false
    }
}
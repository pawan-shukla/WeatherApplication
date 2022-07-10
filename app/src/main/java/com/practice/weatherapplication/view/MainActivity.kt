package com.practice.weatherapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.practice.weatherapplication.databinding.ActivityMainBinding
import com.practice.weatherapplication.model.WeatherData
import com.practice.weatherapplication.vm.WeatherVm

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherVm: WeatherVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewClickListener()
        observeViewModel()
    }

    private fun setViewClickListener() {
        // View Weather button click listener
        binding.layoutInput.apply {
            btnViewWeather.setOnClickListener {
//                val selectedCityId = cityList[spinner.selectedItemPosition].id
                val cityId = 1270644
//                weatherVm.fetchWeatherById(cityId) // fetch weather info
                weatherVm.fetchWeatherByName("Cairns,IN")
            }
        }
    }

    private fun observeViewModel() {
        weatherVm.weatherData.observe(this, Observer {
            it?.let {
                setWeatherInfo(it)
            }
        })

        weatherVm.error.observe(this, Observer { errorMsg ->
            binding.apply {
                if(!errorMsg.isNullOrEmpty()){
                    outputGroup.visibility = View.GONE
                    tvErrorMessage.visibility = View.VISIBLE
                    tvErrorMessage.text = errorMsg
                }
            }
        })

        weatherVm.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                binding.apply {
                    if (it) {
                        outputGroup.visibility = View.GONE
                        tvErrorMessage.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    } else {
                        progressBar.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setWeatherInfo(weatherData: WeatherData) {
        binding.apply {
            outputGroup.visibility = View.VISIBLE
            tvErrorMessage.visibility = View.GONE

            layoutWeatherBasic.apply {
                tvDateTime.text = weatherData.dateTime
                tvTemperature.text = weatherData.temperature
                tvCityCountry.text = weatherData.cityAndCountry
                Glide.with(this@MainActivity).load(weatherData.weatherConditionIconUrl)
                    .into(ivWeatherCondition)
                tvWeatherCondition.text = weatherData.weatherConditionIconDescription
            }

            layoutWeatherAdditional.apply {
                tvHumidityValue.text = weatherData.humidity
                tvPressureValue.text = weatherData.pressure
                tvVisibilityValue.text = weatherData.visibility
            }

            layoutSunsetSunrise.apply {
                tvSunriseTime.text = weatherData.sunrise
                tvSunsetTime.text = weatherData.sunset
            }

        }
    }
}
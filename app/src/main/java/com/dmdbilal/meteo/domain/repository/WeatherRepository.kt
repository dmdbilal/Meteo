package com.dmdbilal.meteo.domain.repository

import com.dmdbilal.meteo.domain.util.Resource
import com.dmdbilal.meteo.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}
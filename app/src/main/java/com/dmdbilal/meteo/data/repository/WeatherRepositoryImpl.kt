package com.dmdbilal.meteo.data.repository

import com.dmdbilal.meteo.data.mappers.toWeatherInfo
import com.dmdbilal.meteo.data.remote.WeatherApi
import com.dmdbilal.meteo.domain.repository.WeatherRepository
import com.dmdbilal.meteo.domain.util.Resource
import com.dmdbilal.meteo.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        lateinit var res: Resource<WeatherInfo>
        try {
            val data = api.getWeatherData(
                lat = lat, long = long
            )
            res = Resource.Success(
                data.toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            res = Resource.Error(e.message ?: "UNKNOWN ERROR")
        } finally {
            println("From repo: " + res.data)
            return res
        }

    }
}
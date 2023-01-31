package com.dmdbilal.meteo.domain.weather

import com.dmdbilal.meteo.domain.util.WeatherType
import java.time.LocalDateTime

// Data for one hour.
data class WeatherData (
    val time: LocalDateTime,
    val temperature: Double,
    val humidity: Double,
    val windSpeed: Double,
    val weatherType: WeatherType
)
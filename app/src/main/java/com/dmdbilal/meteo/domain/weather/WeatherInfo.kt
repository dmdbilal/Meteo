package com.dmdbilal.meteo.domain.weather

// Contains weather data for per day
data class WeatherInfo(
    // Weather data for the list of days.
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    // That hour forecast
    val currentWeatherData: WeatherData?
)

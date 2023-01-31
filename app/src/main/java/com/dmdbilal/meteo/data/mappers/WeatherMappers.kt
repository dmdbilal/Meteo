package com.dmdbilal.meteo.data.mappers

import com.dmdbilal.meteo.data.remote.WeatherDataDto
import com.dmdbilal.meteo.data.remote.WeatherDto
import com.dmdbilal.meteo.domain.weather.WeatherData
import com.dmdbilal.meteo.domain.weather.WeatherInfo
import com.dmdbilal.meteo.domain.util.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// For mapping the data to its current date.
private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

// Mapper function to map dto to da ta object.

// TODO: extends the list for a whole week.
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val humidity = humidities[index]
        val windSpeed = windSpeeds[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperature = temperature,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24               // Mapping to per day.
    }.mapValues {
        it.value.map { it.data }    // Mapping each IndexedWeatherData to WeatherData.
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()

    println("weatherDataMap : " + weatherDataMap)


    // Getting data for current hour.
    val currentWeatherData = weatherDataMap[0]?.find {// Runs until it finds or till end.
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    println("currentWeatherData: " + currentWeatherData)
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}
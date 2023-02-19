package com.dmdbilal.meteo

import com.dmdbilal.meteo.data.di.AppModule.provideWeatherApi
import com.dmdbilal.meteo.data.remote.WeatherApi
import org.junit.Before
import org.junit.Test

class ExampleUnitTest {
    private lateinit var api: WeatherApi
    @Before
    fun setup() {
        api = provideWeatherApi()
    }
    @Test
    fun `test retrofit`() {
        assert(api != null)
    }
}
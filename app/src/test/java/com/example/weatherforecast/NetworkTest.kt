package com.example.weatherforecast

import com.example.weatherforecast.dataClasses.City
import com.example.weatherforecast.dataClasses.MyResponse
import com.example.weatherforecast.dataClasses.WeatherData
import com.example.weatherforecast.network.WeatherApi
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test
import retrofit2.Response

class NetworkTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    val weatherApi = WeatherApi()
    val weatherDataList = mockk<List<WeatherData>>()
    val city =  mockk<City>()

    @Test
    fun getWeatherKarlsruhe()= runBlocking{
        assertEquals(weatherApi.getRetrofitApi().getWeatherKarlsruhe().javaClass, MyResponse("","",weatherDataList,"",city).javaClass)
    }
    @Test
    fun getWeatherRustavi()= runBlocking{
        assertEquals(weatherApi.getRetrofitApi().getWeatherRustavi().javaClass, MyResponse("","",weatherDataList,"",city).javaClass)
    }
    @Test
    fun getWeatherMariupol()= runBlocking{
        assertEquals(weatherApi.getRetrofitApi().getWeatherMariupol().javaClass, MyResponse("","",weatherDataList,"",city).javaClass)
    }
    @Test
    fun getWeatherKelowna()= runBlocking{
        assertEquals(weatherApi.getRetrofitApi().getWeatherKelowna().javaClass, MyResponse("","",weatherDataList,"",city).javaClass)
    }
}
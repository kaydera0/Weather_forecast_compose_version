package com.example.weatherforecast.utils

import com.example.weatherforecast.dataClasses.*
import com.example.weatherforecast.network.WeatherApi
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ResponseDecoderTest {

    val responseDecoder = ResponseDecoder(WeatherApi())
    val response = createResponse()

    @Test
    fun getMinTemp() {
        assertTrue(responseDecoder.getMinTemp(response, 1).toInt() != 0)
    }

    @Test
    fun getMaxTemp() {
        assertTrue(responseDecoder.getMaxTemp(response, 1).toInt() != 0)
    }
}

fun createResponse(): MyResponse {
    val main = Main("280", "275", "290", "65")
    val weather = Weather("main", "description", "04d")
    val city = City("name", "10800")
    val weatherList = ArrayList<Weather>()
    repeat(40) {
        weatherList.add(weather)
    }
    val weatherData = WeatherData(
        main,
        weatherList,
        "dt_txt"
    )
    val weatherDataList = ArrayList<WeatherData>()
    repeat(40) {
        weatherDataList.add(weatherData)
    }

    val response = MyResponse(
        "val cod: String",
        "val message: String",
        weatherDataList,
        "val dt_txt: String",
        city
    )
    return response
}

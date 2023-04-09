package com.example.weatherforecast.network

import com.example.weatherforecast.dataClasses.MyResponse
import com.example.weatherforecast.dataClasses.WeatherData
import retrofit2.http.GET


interface RetrofitApi {

    @GET("/data/2.5/forecast?id=701822&appid=292f3c7b941d36f41679b6ded29cac80")
    suspend fun getWeatherMariupol(): MyResponse

    @GET("/data/2.5/forecast?id=612287&appid=292f3c7b941d36f41679b6ded29cac80")
    suspend fun getWeatherRustavi(): MyResponse

    @GET("/data/2.5/forecast?id=2892794&appid=292f3c7b941d36f41679b6ded29cac80")
    suspend fun getWeatherKarlsruhe(): MyResponse

    @GET("/data/2.5/forecast?id=5990579&appid=292f3c7b941d36f41679b6ded29cac80")
    suspend fun getWeatherKelowna(): MyResponse

    @GET("/data/2.5/forecast?id=524901&appid=292f3c7b941d36f41679b6ded29cac80")
    suspend fun getWeather(): MyResponse
}




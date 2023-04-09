package com.example.weatherforecast.dataClasses

data class MyResponse(
    val cod: String,
    val message: String,
    val list: List<WeatherData>,
    val dt_txt: String,
    val city:City
)
data class WeatherData(
    val main: Main,
    val weather: List<Weather>,
    val dt_txt: String
    )

data class Main(val temp: String, val temp_min: String, val temp_max: String, val humidity: String)
data class Weather(val main: String, val description: String,val icon:String)
data class City(val name:String,val timezone:String)
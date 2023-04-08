package com.example.weatherforecast.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class WeatherApi {
    private val API_KEY = "292f3c7b941d36f41679b6ded29cac80"
    private val url = "http://api.openweathermap.org/data/2.5/forecast?id=524901&appid=292f3c7b941d36f41679b6ded29cac80"
    private val URL = "http://api.openweathermap.org"

    private fun getRetrofitApi(): RetrofitApi {
        val gson = GsonBuilder().setLenient().create()
        val retrofitSimpleCast = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofitSimpleCast.create(RetrofitApi::class.java)
    }
    suspend fun getWeather(){
        val response = getRetrofitApi().getWeather()
        val q = Gson().fromJson(response.list[0].toString(),WeatherData::class.java)
        Log.d("MY_TAG", q.toString())
        Log.d("MY_TAG", response.list[0].toString())
    }
}
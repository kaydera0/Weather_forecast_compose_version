package com.example.weatherforecast.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class WeatherApi {

    private val URL = "http://api.openweathermap.org"

    fun getRetrofitApi(): RetrofitApi {
        val gson = GsonBuilder().setLenient().create()
        val retrofitSimpleCast = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofitSimpleCast.create(RetrofitApi::class.java)
    }
}
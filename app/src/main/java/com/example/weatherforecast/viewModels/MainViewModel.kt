package com.example.weatherforecast.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.dataClasses.CityData
import com.example.weatherforecast.network.WeatherApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val weatherApi: WeatherApi):ViewModel() {

    val currentCity : MutableLiveData<CityData>? = null

    fun getWeater(){
        viewModelScope.launch {
            weatherApi.getWeather()
        }

    }
}
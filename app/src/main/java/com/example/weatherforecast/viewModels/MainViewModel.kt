package com.example.weatherforecast.viewModels

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.dataClasses.CityData
import com.example.weatherforecast.utils.ResponseDecoder
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val responseDecoder: ResponseDecoder,
    @ApplicationContext context: Context
) : ViewModel() {

    val currentCity = MutableLiveData<CityData>()
    val cityList = MutableLiveData<List<CityData>>()
    val networkStatus = MutableLiveData(false)
    var lastKnownLocation = MutableLiveData<Location>()

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    init {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() = networkStatus.postValue(false)
            override fun onAvailable(network: android.net.Network) = networkStatus.postValue(true)
            override fun onLost(network: android.net.Network) = networkStatus.postValue(false)
        }
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkStatusCallback)

        viewModelScope.launch { updateCityList() }

    }

    suspend fun updateCityList() {
        cityList.postValue(responseDecoder.getCityDataList())
    }

    @SuppressLint("MissingPermission")
    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    lastKnownLocation.value = task.result
                }
            }
        } catch (e: SecurityException) {
            println(e)
        }
    }
}
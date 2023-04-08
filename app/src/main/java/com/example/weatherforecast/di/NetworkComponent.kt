package com.example.weatherforecast.di


import com.example.weatherforecast.network.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkComponent {

    @Singleton
    @Provides
    fun provideNetwork(): WeatherApi {
        return WeatherApi()
    }
}
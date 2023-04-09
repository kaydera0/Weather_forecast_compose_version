package com.example.weatherforecast.di

import com.example.weatherforecast.network.WeatherApi
import com.example.weatherforecast.utils.ResponseDecoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsComponent {

    @Singleton
    @Provides
    fun provideNUtils(): ResponseDecoder {
        return ResponseDecoder(weatherApi = WeatherApi())
    }
}
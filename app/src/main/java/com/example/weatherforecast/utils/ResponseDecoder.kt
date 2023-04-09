package com.example.weatherforecast.utils

import com.example.weatherforecast.dataClasses.CityData
import com.example.weatherforecast.dataClasses.MyResponse
import com.example.weatherforecast.dataClasses.WeeklyWeather
import com.example.weatherforecast.network.WeatherApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ResponseDecoder @Inject constructor(val weatherApi: WeatherApi) {


    suspend fun getCityDataList(): List<CityData> {
        val list = ArrayList<CityData>()

        val responseKarlsruhe = weatherApi.getRetrofitApi().getWeatherKarlsruhe()
        list.add(responseToDataClass(responseKarlsruhe))
        val responseRustavi = weatherApi.getRetrofitApi().getWeatherRustavi()
        list.add(responseToDataClass(responseRustavi))
        val responseMariupol = weatherApi.getRetrofitApi().getWeatherMariupol()
        list.add(responseToDataClass(responseMariupol))
        val responseKelowna = weatherApi.getRetrofitApi().getWeatherKelowna()
        list.add(responseToDataClass(responseKelowna))
        return list
    }

    private fun responseToDataClass(response: MyResponse): CityData {
        return CityData(
            name = response.city.name,
            time = timezoneToTime(response.city.timezone),
            temperature = (response.list[0].main.temp.toFloat().toInt() - 273).toString(),
            day = getWeekday(response.city.timezone),
            weatherDescription = response.list[0].weather[0].description,
            weeklyWeatherArrayList = getWeaklyWeatherArr(response.city.timezone, response)

        )
    }

    private fun timezoneToTime(timezone: String): String {
        val UTCtime = LocalDateTime.now(ZoneId.of("UTC"))
        val currentPlaceTime = UTCtime.plusSeconds(timezone.toLong())
        val format = DateTimeFormatter.ofPattern("hh:mm")
        return currentPlaceTime.format(format)
    }

    private fun getWeekday(timezone: String): String {
        val day = LocalDateTime.now(ZoneId.of("UTC")).plusSeconds(timezone.toLong()).dayOfWeek
        return day.toString()
    }

    private fun getWeaklyWeatherArr(
        timezone: String,
        response: MyResponse
    ): ArrayList<WeeklyWeather> {
        val list = ArrayList<WeeklyWeather>()
        for (i in 0..4) {
            val day = LocalDateTime.now(ZoneId.of("UTC"))
                .plusSeconds(timezone.toLong()).dayOfWeek.plus(i.toLong())
            val weeklyWeather = WeeklyWeather(
                weekday = day.toString(),
                weekHumidity = response.list[i * 6].main.humidity,
                weekMinTemp = getMinTemp(response, i),
                weekMaxTemp = getMaxTemp(response, i),
                iconCode = response.list[i * 6].weather[0].icon
            )
            list.add(weeklyWeather)
        }
        return list
    }

    fun getMinTemp(response: MyResponse, index: Int): String {
        val list = ArrayList<Int>()
        val arrMinValues = IntArray(5)
        var count = 0
        var count2 = 0
        for (i in 0..38) {
            if (response.list[count].dt_txt.dropLast(9) == response.list[i].dt_txt.dropLast(9)) {
                list.add(response.list[i].main.temp_min.toFloat().toInt())
                if (i == 38) {
                    arrMinValues[count2++] = list.min()
                }
            } else {
                list.add(response.list[i].main.temp_min.toFloat().toInt())
                arrMinValues[count2++] = list.min()
                list.clear()
                count = i
            }
        }
        return (arrMinValues[index] - 273).toString()
    }

    fun getMaxTemp(response: MyResponse, index: Int): String {
        val arrMasValues = IntArray(5)
        val list = ArrayList<Int>()
        var count = 0
        var count2 = 0
        for (i in 0..38) {
            if (response.list[count].dt_txt.dropLast(9) == response.list[i].dt_txt.dropLast(9)) {
                list.add(response.list[i].main.temp_max.toFloat().toInt())
                if (i == 38) {
                    arrMasValues[count2++] = list.max()
                }
            } else {
                list.add(response.list[i].main.temp_max.toFloat().toInt())
                arrMasValues[count2++] = list.max()
                list.clear()
                count = i
            }
        }
        return (arrMasValues[index] - 273).toString()
    }
}
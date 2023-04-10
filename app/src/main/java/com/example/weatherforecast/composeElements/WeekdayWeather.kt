package com.example.weatherforecast.composeElements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherforecast.dataClasses.WeeklyWeather

@Composable
fun weekdayWeather(weeklyWeather: WeeklyWeather) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = weeklyWeather.weekday,
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(vertical = 5.dp)
        )
        AsyncImage(
            model = "http://openweathermap.org/img/w/${weeklyWeather.iconCode}.png",
            contentDescription = "logo",
            modifier = Modifier.height(40.dp)
        )
        Text(
            text = weeklyWeather.weekHumidity + "%",
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(
            text = weeklyWeather.weekMaxTemp,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 50.dp)
        )
        Text(
            text = weeklyWeather.weekMinTemp,
            fontSize = 20.sp,
            color = Color.LightGray,
            modifier = Modifier.padding(start = 30.dp)
        )
    }
}
package com.example.weatherforecast.composeElements

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.R
import com.example.weatherforecast.dataClasses.CityData

@Composable
fun cityWeatherScreen(cityData: CityData) {

    Box(
        Modifier
            .fillMaxSize()
            .paint(
                painterResource(
                    id = when (cityData.name) {
                        "Rustavi" -> R.mipmap.georgia
                        "Mariupol" -> R.mipmap.mariupol
                        "Karlsruhe" -> R.mipmap.karlsruhe
                        else -> R.mipmap.kelowna
                    }
                ), contentScale = ContentScale.FillBounds
            )
    )

    Surface(
        color = Color.Gray.copy(0.5f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, bottom = 20.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = cityData.temperature,
                    fontSize = 100.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                Column(Modifier.height(100.dp)) {
                    Text(
                        text = stringResource(id = R.string.c),
                        fontSize = 40.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    Spacer(modifier = Modifier)
                }
            }
            Text(
                text = cityData.name,
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 70.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = cityData.day,
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                text = cityData.time,
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = cityData.weatherDescription,
                Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column() {
                    for (i in 0..cityData.weeklyWeatherArrayList.size - 1) {
                        weekdayWeather(weeklyWeather = cityData.weeklyWeatherArrayList[i])
                        Divider(
                            thickness = 1.dp,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
                        )
                    }

                }
            }
        }
    }
}
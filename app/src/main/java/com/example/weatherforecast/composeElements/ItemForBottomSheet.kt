package com.example.weatherforecast.composeElements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.dataClasses.CityData
import com.example.weatherforecast.viewModels.MainViewModel

@Composable
fun itemForBottomSheet(cityData: CityData,vm:MainViewModel){
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            vm.currentCity.value =cityData
        }) {
        Row {
            Column(
                Modifier
                    .padding(start = 30.dp)
                    .fillMaxWidth(0.8f)) {
                Text(text = cityData.name, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = cityData.time, color = Color.White)
            }
            Text(text = cityData.temperature, fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = "c", color = Color.White)
        }

    }
    Divider(thickness = 2.dp, color = Color.White, modifier = Modifier.padding(horizontal = 20.dp))
}
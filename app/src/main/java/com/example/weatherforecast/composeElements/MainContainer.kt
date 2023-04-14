@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.weatherforecast.composeElements

import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.dataClasses.CityData
import com.example.weatherforecast.viewModels.MainViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*


@Composable
fun mainContainer(vm: MainViewModel) {

    val defaultLoc = Location("")
    defaultLoc.latitude = 0.0
    defaultLoc.longitude = 0.0
    val currentPos: State<Location?> =
        vm.lastKnownLocation.observeAsState(initial = defaultLoc)
    val cityList: State<List<CityData>?> =
        vm.cityList.observeAsState(initial = null)
    val currentCity : State<CityData?> = vm.currentCity.observeAsState()

    if(currentCity.value == null) {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            cameraPositionState = CameraPositionState(
                position = CameraPosition(
                    LatLng(
                        currentPos.value?.latitude!!,
                        currentPos.value?.longitude!!
                    ), 10f, 1.0f, 1.0f
                )
            )
        ) {
        }
        BottomSheetScaffold(sheetContent = {
            Column(Modifier.padding(top = 0.dp, bottom = 50.dp)) {
                Divider(
                    thickness = 4.dp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 50.dp).testTag("BOTTOM_SHEET_DIVIDER")
                )
                if (cityList.value != null) {
                    for (i in 0..cityList.value?.size!! - 1) {
                        itemForBottomSheet(cityData = cityList.value!!.get(i),vm)
                    }
                }
            }
        }, sheetContainerColor = Color.DarkGray, modifier = Modifier.testTag("BOTTOM_SHEET")) {
        }
    }
    else {
        cityWeatherScreen(cityData = currentCity.value!!)
    }
}
package com.example.weatherforecast.composeElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.weatherforecast.R

@Composable
fun errorScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .alpha(0.5f), contentAlignment = Alignment.Center
    ) {
        Column() {
            Image(
                painter = painterResource(id = com.google.android.material.R.drawable.mtrl_ic_error),
                contentDescription = "error"
            )
            Text(text = stringResource(id = R.string.error))

        }
    }
}
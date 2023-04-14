package com.example.weatherforecast

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.window.testing.layout.WindowLayoutInfoPublisherRule
import com.example.weatherforecast.activities.MainActivityCompose
import com.example.weatherforecast.composeElements.errorScreen
import com.example.weatherforecast.composeElements.mainContainer
import com.example.weatherforecast.viewModels.MainViewModel
import com.microsoft.device.dualscreen.testing.compose.simulateVerticalFoldingFeature
import io.mockk.mockk
import kotlinx.coroutines.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


class ComposeUITests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivityCompose>()


    @Test
    fun sample_verticalFoldingFeature_showsTwoPanes() {
        Thread.sleep(2000)
//        composeTestRule.onNodeWithText("Kelowna").assertIsDisplayed()
//        composeTestRule.onNodeWithTag("BOTTOM_SHEET").performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTag("BOTTOM_SHEET").performTouchInput { swipeUp() }
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("Kelowna").performClick()
        Thread.sleep(2000)


    }
}
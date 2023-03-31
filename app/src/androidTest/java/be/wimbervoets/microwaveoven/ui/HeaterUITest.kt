package be.wimbervoets.microwaveoven.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import be.wimbervoets.microwaveoven.controller.heater.Heater
import org.junit.Rule
import org.junit.Test

class HeaterUITest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testToggleHeaterOnOff() {
        val heater = Heater()
        heater.turnOnHeater()
        composeRule.setContent {
            HeaterUI(heater)
        }
        composeRule.onNodeWithContentDescription("Heating..").assertIsDisplayed()

        heater.turnOffHeater()

        composeRule.onNodeWithContentDescription("Heating..").assertDoesNotExist()
    }
}
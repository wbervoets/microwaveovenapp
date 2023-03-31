package be.wimbervoets.microwaveoven.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import be.wimbervoets.microwaveoven.MicrowaveViewModel
import org.junit.Rule
import org.junit.Test

class MicrowaveUITest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun whenStartButtonIsClickedTheMicrowaveOvenStartsHeatingFor60Seconds() {
        val microwaveViewModel = MicrowaveViewModel()
        composeRule.setContent {
            MicrowaveUI(viewModel = microwaveViewModel)
        }
        composeRule.onNodeWithText("START", substring = true).performClick()
        composeRule.onNodeWithText("sec", substring = true).assertTextContains("60", substring = true)
        composeRule.onNodeWithContentDescription("Heating..").assertIsDisplayed()
    }
}
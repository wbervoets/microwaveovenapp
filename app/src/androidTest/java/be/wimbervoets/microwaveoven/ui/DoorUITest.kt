package be.wimbervoets.microwaveoven.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import be.wimbervoets.microwaveoven.controller.door.Door
import org.junit.Rule
import org.junit.Test

class DoorUITest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testOpenDoor() {
        composeRule.setContent {
            DoorUI(door = Door().apply { openDoor() })
        }

        composeRule.onNodeWithTag(testTag = "doorStatus")
            .assertIsDisplayed()
            .assertTextEquals("Door is OPEN")
    }

    @Test
    fun testClosedDoor() {
        composeRule.setContent {
            DoorUI(door = Door())
        }

        composeRule.onNodeWithTag(testTag = "doorStatus")
            .assertIsDisplayed()
            .assertTextEquals("Door is CLOSED")
    }

}
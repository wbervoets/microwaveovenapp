package be.wimbervoets.microwaveoven.controller.light

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("LED Lightbulb test")
@OptIn(ExperimentalCoroutinesApi::class)
class LEDLightBulbTest {
    @Test
    fun `turning off the light emits a light off state`() = runTest {
        val lightBulb: ILightBulb = LEDLightBulb()
        lightBulb.turnLightOff()
        lightBulb.lightBulbState.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `a light is turned off by default`() = runTest {
        val lightBulb: ILightBulb = LEDLightBulb()
        lightBulb.lightBulbState.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `turning on the light emits a light on state`() = runTest {
        val lightBulb: ILightBulb = LEDLightBulb()
        lightBulb.turnLightOn()
        lightBulb.lightBulbState.test {
            assertTrue(awaitItem())
        }
    }

    @Test
    fun `turning on and off the light emits two light states`() = runTest {
        val lightBulb: ILightBulb = LEDLightBulb()
        lightBulb.turnLightOn()
        lightBulb.turnLightOff()
        lightBulb.lightBulbState.test {
            assertFalse(awaitItem())
        }
    }
}
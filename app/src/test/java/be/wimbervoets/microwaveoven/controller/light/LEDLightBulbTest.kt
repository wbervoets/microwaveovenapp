package be.wimbervoets.microwaveoven.controller.light

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("LED Lightbulb test")
@OptIn(ExperimentalCoroutinesApi::class)
class LEDLightBulbTest  {

    private lateinit var lightBulb: ILightBulb

    @BeforeEach
    fun setup() {
        lightBulb = LEDLightBulb()
    }

    @Test
    fun `turning off the light emits a light off state`() = runTest {
        lightBulb.turnLightOff()
        lightBulb.lightBulbState.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `a light is turned off by default`() = runTest {
        lightBulb.lightBulbState.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `turning on the light emits a light on state`() = runTest {
        lightBulb.turnLightOn()
        lightBulb.lightBulbState.test {
            assertTrue(awaitItem())
        }
    }

    @Test
    fun `turning on and off the light emits two light states`() = runTest {
        lightBulb.turnLightOn()
        lightBulb.turnLightOff()
        lightBulb.lightBulbState.test {
            assertFalse(awaitItem())
        }
    }
}
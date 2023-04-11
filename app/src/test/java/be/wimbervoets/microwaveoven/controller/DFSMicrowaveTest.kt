package be.wimbervoets.microwaveoven.controller

import app.cash.turbine.test
import be.wimbervoets.microwaveoven.controller.door.Door
import be.wimbervoets.microwaveoven.controller.heater.Heater
import be.wimbervoets.microwaveoven.controller.light.LEDLightBulb
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("DFS Microwave test")
@OptIn(ExperimentalCoroutinesApi::class)
class DFSMicrowaveTest {
    private lateinit var microwave: DFSMicrowave

    @BeforeEach
    fun setupMicrowave() {
        microwave = DFSMicrowave(
            Door(),
            Heater(),
            LEDLightBulb()
        )
    }

    @Test
    fun `when i open the door the light turns on`() = runTest {
        microwave.openDoor()
        microwave.lightBulb.lightBulbState.test {
            assertTrue(awaitItem())
        }
    }

    @Test
    fun `when i close the door the light turns off`() = runTest {
        microwave.closeDoor()
        microwave.lightBulb.lightBulbState.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `when i open the door the heater stops working`() = runTest {
        microwave.pressStartButton()
        delay(100)
        assertTrue(microwave.isHeating())
        microwave.isHeating.test {
            assertTrue(awaitItem())
        }

        microwave.openDoor()

        assertFalse(microwave.isHeating())
        microwave.isHeating.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `when i press start and the door is open nothing happens`() = runTest {
        microwave.openDoor()
        microwave.pressStartButton()

        assertFalse(microwave.isHeating())
        microwave.isHeating.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `when i press start and the door is closed the heater starts for 60 seconds`() = runTest {
        microwave.pressStartButton()
        microwave.countDownInSeconds.test {
            assertEquals(60, awaitItem())
        }
    }

    @Test
    fun `when i press start and the door is closed and the heater is working the timer is increased with 60 seconds`() = runTest {
        microwave.pressStartButton()
        microwave.pressStartButton()
        microwave.countDownInSeconds.test {
            assertEquals(120, awaitItem())
        }
    }

    @Test
    @Disabled("Takes 1 minute to complete because the timer we use is using real wall time clock")
    fun `when the timer reaches zero the heater is turned off`() = runTest {
        microwave.pressStartButton()
        microwave.countDownTimer.countDownInSeconds.test {
            skipItems(60)
            assertEquals(0, awaitItem())
            assertFalse(microwave.isHeating())
        }
    }

    @Test
    fun `when start button was pressed startButtonPressed flow item is received`() = runTest {
        microwave.pressStartButton() // replay = 1, so this will be collected if the receiver registers later
        microwave.startButtonPressed.test {
            assertEquals(Unit, awaitItem())
        }
    }
}
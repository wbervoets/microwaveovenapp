package be.wimbervoets.microwaveoven.controller.heater

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Heater test")
@OptIn(ExperimentalCoroutinesApi::class)
class HeaterTest {
    @Test
    fun `turning off the heater emits a heater state not heating`() = runTest {
        val heater: IHeater = Heater()
        heater.turnOffHeater()
        heater.isHeating.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `turning on the heater emits a heater state heating`() = runTest {
        val heater: IHeater = Heater()
        heater.turnOnHeater()
        heater.isHeating.test {
            assertTrue(awaitItem())
        }
    }

    @Test
    fun `turning on and off the heater emits two heater states`() = runTest {
        val heater: IHeater = Heater()
        heater.turnOnHeater()
        heater.isHeating.test {
            assertTrue(awaitItem())
        }
        heater.turnOffHeater()
        heater.isHeating.test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun `when heater is turned on and off it is not heating`() {
        val heater: IHeater = Heater()
        heater.turnOnHeater()
        heater.turnOffHeater()
        assertFalse(heater.isHeating())
    }

    @Test
    fun `when heater is turned on it is heating`() {
        val heater: IHeater = Heater()
        heater.turnOnHeater()
        assertTrue(heater.isHeating())
    }

    @Test
    fun `default heater state is not heating`() {
        val heater: IHeater = Heater()
        assertFalse(heater.isHeating())
    }

    @Test
    fun `default heater is not heating`() = runTest {
        val heater: IHeater = Heater()
        heater.isHeating.test {
            assertFalse(awaitItem())
        }
    }
}
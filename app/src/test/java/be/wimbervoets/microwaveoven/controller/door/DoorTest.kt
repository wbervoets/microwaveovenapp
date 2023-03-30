package be.wimbervoets.microwaveoven.controller.door

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Door test")
@OptIn(ExperimentalCoroutinesApi::class)
class DoorTest {

    @Test
    fun `closing the door emits a door state closed`() = runTest {
        val door: IDoor = Door()
        door.doorStateChanged.test {
            // https://github.com/cashapp/turbine#order-of-execution--shared-flows
            door.closeDoor()
            assertEquals(DoorState.CLOSED, awaitItem())
        }
    }

    @Test
    fun `opening the door emits a door state opened`() = runTest {
        val door: IDoor = Door()
        door.doorStateChanged.test {
            // https://github.com/cashapp/turbine#order-of-execution--shared-flows
            door.openDoor()
            assertEquals(DoorState.OPEN, awaitItem())
        }
    }

    @Test
    fun `opening and closing the door emits two door states`() = runTest {
        val door: IDoor = Door()
        door.doorStateChanged.test {
            // https://github.com/cashapp/turbine#order-of-execution--shared-flows
            door.openDoor()
            assertEquals(DoorState.OPEN, awaitItem())
            door.closeDoor()
            assertEquals(DoorState.CLOSED, awaitItem())
        }
    }

    @Test
    fun `closed door returns false for isDoorOpen`() {
        val door: IDoor = Door()
        door.closeDoor()
        assertFalse(door.isDoorOpen())
    }

    @Test
    fun `open door returns true for isDoorOpen`() {
        val door: IDoor = Door()
        door.openDoor()
        assertTrue(door.isDoorOpen())
    }

    @Test
    fun `default door is closed`() {
        val door: IDoor = Door()
        assertFalse(door.isDoorOpen())
    }
}
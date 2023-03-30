package be.wimbervoets.microwaveoven.controller.door

import kotlinx.coroutines.flow.SharedFlow

interface IDoor {
    fun openDoor()
    fun closeDoor()
    fun isDoorOpen(): Boolean
    val doorStateChanged: SharedFlow<DoorState>
}
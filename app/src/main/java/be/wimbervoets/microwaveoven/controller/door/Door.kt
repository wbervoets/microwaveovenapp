package be.wimbervoets.microwaveoven.controller.door

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import timber.log.Timber

class Door(private var state: DoorState = DoorState.CLOSED): IDoor {
    override fun closeDoor() {
        state = DoorState.CLOSED
        _doorStateChanged.tryEmit(DoorState.CLOSED)
        // .emit is a suspendable function, could make closeDoor suspendable if it would take a wile
        Timber.d("Closed door")
    }

    override fun openDoor() {
        state = DoorState.OPEN
        _doorStateChanged.tryEmit(DoorState.OPEN)
        Timber.d("Opened door")
    }

    private val _doorStateChanged = MutableSharedFlow<DoorState>(replay = 1) // could also use StateFlow where replay is 1 by default
    override val doorStateChanged: SharedFlow<DoorState> = _doorStateChanged

    override fun isDoorOpen(): Boolean = state == DoorState.OPEN
}
package be.wimbervoets.microwaveoven.controller

import be.wimbervoets.microwaveoven.controller.door.DoorState
import be.wimbervoets.microwaveoven.controller.door.IDoor
import be.wimbervoets.microwaveoven.controller.heater.IHeater
import be.wimbervoets.microwaveoven.controller.light.ILightBulb
import be.wimbervoets.microwaveoven.controller.timer.MicrowaveCountDownTimer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

// allows constructing DFS Microwave with any door and heater implementing the IDoor, IHeater interfaces
class DFSMicrowave(
    override val door: IDoor,
    override val heater: IHeater,
    override val lightBulb: ILightBulb): IMicrowave {

    override val countDownTimer = MicrowaveCountDownTimer(onFinished = {
        // When timer went to zero...
        heater.turnOffHeater()
    })

    override fun openDoor() {
        door.openDoor()
        lightBulb.turnLightOn()
        heater.turnOffHeater()
        countDownTimer.cancelCountDownTimer()
    }

    override fun closeDoor() {
        door.closeDoor()
        lightBulb.turnLightOff()
    }

    override fun isDoorOpen(): Boolean = door.isDoorOpen()

    override val doorStateChanged: SharedFlow<DoorState>
        get() = door.doorStateChanged

    override fun turnOnHeater() = heater.turnOnHeater()
    override fun turnOffHeater() = heater.turnOffHeater()
    override fun isHeating(): Boolean = heater.isHeating()
    override val isHeating: SharedFlow<Boolean> = heater.isHeating
    override val lightBulbState: SharedFlow<Boolean> = lightBulb.lightBulbState

    override fun turnLightOn() {
        lightBulb.turnLightOn()
    }

    override fun turnLightOff() {
        lightBulb.turnLightOff()
    }

    override fun startCountDownTimer() {
        countDownTimer.startCountDownTimer()
    }

    override fun cancelCountDownTimer() {
        countDownTimer.cancelCountDownTimer()
    }

    override fun incrementCountDownTimerDuration(duration: Duration) {
        countDownTimer.incrementCountDownTimerDuration(duration)
    }

    override val countDownInSeconds: StateFlow<Int> = countDownTimer.countDownInSeconds

    override fun pressStartButton() {
        _startButtonPressed.value = Unit

        if (!isDoorOpen()) {
            if (heater.isHeating()) {
                Timber.d("Increase timer with 1 minute")
                countDownTimer.incrementCountDownTimerDuration(1.minutes) // thread safe?
            } else {
                heater.turnOnHeater()
                countDownTimer.incrementCountDownTimerDuration(1.minutes)
                countDownTimer.startCountDownTimer()
            }
        } else {
            Timber.d("Nothing happens when the door is open")
        }
    }

    // TODO: Why startButtonPressed: StateFlow<Unit> is needed?
    private val _startButtonPressed = MutableStateFlow(Unit)
    override val startButtonPressed: StateFlow<Unit> = _startButtonPressed

}
package be.wimbervoets.microwaveoven.controller.light

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class LEDLightBulb: ILightBulb {
    // State of the lightbulb: light on or off
    private val _lightBulbState = MutableSharedFlow<Boolean>(1)
    override val lightBulbState: SharedFlow<Boolean> = _lightBulbState

    init {
        _lightBulbState.tryEmit(false) // initially the light bulb is off
    }

    override fun turnLightOn() {
        _lightBulbState.tryEmit(true)
    }

    override fun turnLightOff() {
        _lightBulbState.tryEmit(false)
    }
}
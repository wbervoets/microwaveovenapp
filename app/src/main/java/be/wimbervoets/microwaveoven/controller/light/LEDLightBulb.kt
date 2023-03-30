package be.wimbervoets.microwaveoven.controller.light

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update

class LEDLightBulb: ILightBulb {
    // State of the lightbulb: light on or off
    private val _lightBulbState = MutableStateFlow(false) // initially the light bulb is off
    override val lightBulbState: SharedFlow<Boolean> = _lightBulbState

    override fun turnLightOn() {
        _lightBulbState.update { true }
    }

    override fun turnLightOff() {
        _lightBulbState.update { false }
    }
}
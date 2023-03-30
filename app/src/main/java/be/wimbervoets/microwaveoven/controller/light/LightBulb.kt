package be.wimbervoets.microwaveoven.controller.light

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update

class LightBulb {
    // State of the lightbulb: light on or off
    private val _lightBulbState = MutableStateFlow(false) // initially the light bulb is off
    val lightBulbState: SharedFlow<Boolean> = _lightBulbState

    fun turnLightOn() {
        _lightBulbState.update { true }
    }

    fun turnLightOff() {
        _lightBulbState.update { false }
    }
}
// We could add an ILightBulb interface also here and eg. a LED or TL based implementation
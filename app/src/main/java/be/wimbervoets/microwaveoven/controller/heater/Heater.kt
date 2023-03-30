package be.wimbervoets.microwaveoven.controller.heater

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

class Heater : IHeater {

    private val _isHeating = MutableStateFlow(false)
    override val isHeating: StateFlow<Boolean> = _isHeating

    override fun turnOnHeater() {
        _isHeating.update { true } // with StateFlow we can use update
        Timber.d("Turned on Heater")
    }

    override fun turnOffHeater() {
        _isHeating.update { false }
        Timber.d("Turned off Heater")
    }

    override fun isHeating(): Boolean {
        return isHeating.value
    }
}
package be.wimbervoets.microwaveoven.controller.heater

import kotlinx.coroutines.flow.*
import timber.log.Timber

class Heater(private var heating: Boolean = false) : IHeater {

    private val _isHeating = MutableSharedFlow<Boolean>(replay = 1)
    override val isHeating: SharedFlow<Boolean> = _isHeating

    init {
        _isHeating.tryEmit(false) // default value is no heating
    }

    override fun turnOnHeater() {
        heating = true
        _isHeating.tryEmit(true)
        Timber.d("Turned on Heater")
    }

    override fun turnOffHeater() {
        heating = false
        _isHeating.tryEmit(false)
        Timber.d("Turned off Heater")
    }

    override fun isHeating(): Boolean = heating
}
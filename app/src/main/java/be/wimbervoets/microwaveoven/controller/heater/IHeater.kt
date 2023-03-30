package be.wimbervoets.microwaveoven.controller.heater

import kotlinx.coroutines.flow.StateFlow

interface IHeater {
    fun turnOnHeater()
    fun turnOffHeater()
    fun isHeating(): Boolean
    val isHeating: StateFlow<Boolean>
}
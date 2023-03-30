package be.wimbervoets.microwaveoven.controller.heater

import kotlinx.coroutines.flow.SharedFlow

interface IHeater {
    fun turnOnHeater()
    fun turnOffHeater()
    fun isHeating(): Boolean
    val isHeating: SharedFlow<Boolean>
}
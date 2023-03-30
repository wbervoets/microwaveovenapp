package be.wimbervoets.microwaveoven.controller.light

import kotlinx.coroutines.flow.SharedFlow

interface ILightBulb {
    val lightBulbState: SharedFlow<Boolean>
    fun turnLightOn()
    fun turnLightOff()
}
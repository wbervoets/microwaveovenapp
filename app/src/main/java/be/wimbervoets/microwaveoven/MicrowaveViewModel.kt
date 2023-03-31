package be.wimbervoets.microwaveoven

import androidx.lifecycle.ViewModel
import be.wimbervoets.microwaveoven.controller.DFSMicrowave
import be.wimbervoets.microwaveoven.controller.IMicrowave
import be.wimbervoets.microwaveoven.controller.door.Door
import be.wimbervoets.microwaveoven.controller.door.IDoor
import be.wimbervoets.microwaveoven.controller.heater.Heater
import be.wimbervoets.microwaveoven.controller.heater.IHeater
import be.wimbervoets.microwaveoven.controller.light.ILightBulb
import be.wimbervoets.microwaveoven.controller.light.LEDLightBulb
import timber.log.Timber

class MicrowaveViewModel: ViewModel() {

    // in a real app we could use Dagger Hilt or other dependency injection framework
    // to inject IMicrowave into the viewmodel via constructor injection
    private val door: IDoor = Door()
    private val heater: IHeater = Heater()
    private val lightBulb: ILightBulb = LEDLightBulb()
    val microwave: IMicrowave = DFSMicrowave(door, heater, lightBulb)

    init {
        Timber.d("Init MicrowaveViewModel")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("MicrowaveViewModel cleared")
        microwave.turnLightOff()
        microwave.turnOffHeater()
        microwave.cancelCountDownTimer()
    }
}
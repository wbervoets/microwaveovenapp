package be.wimbervoets.microwaveoven.controller

import be.wimbervoets.microwaveoven.controller.door.IDoor
import be.wimbervoets.microwaveoven.controller.heater.IHeater
import be.wimbervoets.microwaveoven.controller.light.LightBulb
import be.wimbervoets.microwaveoven.controller.timer.MicrowaveCountDownTimer
import kotlinx.coroutines.flow.StateFlow

interface IMicrowave: IDoor, IHeater {
    /*
     I split the IMicrowave interface in multiple smaller interfaces (IDoor, IHeater)
     - door and heater can be replaced inside a microwave with another implementation if needed
     - smaller interface => single responsability => less dependencies/easier to implement/test
     */

    fun pressStartButton()
    val startButtonPressed: StateFlow<Unit> // emits value each time start button is pressed


    val door: IDoor
    val heater: IHeater
    val lightBulb: LightBulb // could also create ILightBulb interface
    val countDownTimer: MicrowaveCountDownTimer // could also create Interface for countdown timer
}
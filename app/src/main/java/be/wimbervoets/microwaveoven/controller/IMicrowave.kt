package be.wimbervoets.microwaveoven.controller

import be.wimbervoets.microwaveoven.controller.door.IDoor
import be.wimbervoets.microwaveoven.controller.heater.IHeater
import be.wimbervoets.microwaveoven.controller.light.ILightBulb
import be.wimbervoets.microwaveoven.controller.timer.ICountDownTimer
import kotlinx.coroutines.flow.StateFlow

interface IMicrowave: IDoor, IHeater, ILightBulb, ICountDownTimer {
    /*
     I split the IMicrowave interface in multiple smaller interfaces (IDoor, IHeater; ILightBulb and ICountDownTimer)
     - door and heater can be replaced inside a microwave with another implementation if needed
     - light bulb can be LED or TL or ...
     - smaller interfaces => single responsability => less dependencies/coupling / easier to implement/test
     */

    fun pressStartButton()
    val startButtonPressed: StateFlow<Unit> // emits value each time start button is pressed


    val door: IDoor
    val heater: IHeater
    val lightBulb: ILightBulb
    val countDownTimer: ICountDownTimer
}
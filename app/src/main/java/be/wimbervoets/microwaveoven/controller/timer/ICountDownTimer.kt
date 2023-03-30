package be.wimbervoets.microwaveoven.controller.timer

import kotlinx.coroutines.flow.StateFlow
import kotlin.time.Duration

interface ICountDownTimer {
    fun startCountDownTimer()
    fun stopCountDownTimer()
    fun incrementCountDownTimerDuration(duration: Duration)
    val countDownInSeconds: StateFlow<Int>
}
package be.wimbervoets.microwaveoven.controller.timer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import kotlin.concurrent.timer
import kotlin.time.Duration

class MicrowaveCountDownTimer(
    private val onFinished: () -> Unit
) {

    private val _countDownInSeconds = MutableStateFlow(0) // Initial state is 0 seconds
    val countDownInSeconds: StateFlow<Int> = _countDownInSeconds // TODO put in interface ICountDownTimer, IMicrowave

    private var timer: Timer? = null

    fun startCountDownTimer() {
        require(timer == null) { "Can only schedule one timer concurrently, call stopCountDownTimer first" }
        timer = timer("Countdown timer", false, period = 1000) {
            if (_countDownInSeconds.value > 0) {
                _countDownInSeconds.tryEmit(_countDownInSeconds.value - 1) // TODO : tryEmit ?
            } else {
                cancel() // cancel the timer when we went to zero
                onFinished.invoke()
            }
        }
    }

    fun stopCountDownTimer() {
        timer?.cancel()
        timer = null
    }

    fun incrementCountDownTimerDuration(duration: Duration) {
        _countDownInSeconds.value = (_countDownInSeconds.value + duration.inWholeSeconds).toInt()
    }

}
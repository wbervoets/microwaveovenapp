package be.wimbervoets.microwaveoven.controller.timer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import kotlin.concurrent.timer
import kotlin.time.Duration

class MicrowaveCountDownTimer(
    private val onFinished: () -> Unit
): ICountDownTimer {

    private val _countDownInSeconds = MutableStateFlow(0) // Initial state is 0 seconds
    override val countDownInSeconds: StateFlow<Int> = _countDownInSeconds

    private var timer: Timer? = null

    override fun startCountDownTimer() {
        require(timer == null) { "Can only schedule one timer concurrently, call stopCountDownTimer first" }
        timer = timer("Countdown timer", false, period = 1000) {
            if (_countDownInSeconds.value > 0) {
                _countDownInSeconds.tryEmit(_countDownInSeconds.value - 1)
            } else {
                cancel() // cancel the timer when we went to zero
                onFinished.invoke()
            }
        }
    }

    override fun stopCountDownTimer() {
        timer?.cancel()
        timer = null
    }

    override fun incrementCountDownTimerDuration(duration: Duration) {
        _countDownInSeconds.value = (_countDownInSeconds.value + duration.inWholeSeconds).toInt()
    }

}
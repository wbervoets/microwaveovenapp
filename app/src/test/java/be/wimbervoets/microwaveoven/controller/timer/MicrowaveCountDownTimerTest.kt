package be.wimbervoets.microwaveoven.controller.timer

import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.seconds

@DisplayName("Microwave CountDownTimer test")
@OptIn(ExperimentalCoroutinesApi::class)
class MicrowaveCountDownTimerTest {
    @Test
    fun `countdown timer is initialized at 0`() = runTest {
        val countDownTimer: ICountDownTimer = MicrowaveCountDownTimer {

        }
        countDownTimer.countDownInSeconds.test {
            assertEquals(0, awaitItem())
        }
    }

    @Test
    fun `if countdown timer is set to 4 seconds it counts down to zero`() = runTest {
        val countDownTimer: ICountDownTimer = MicrowaveCountDownTimer {
            // no op
        }
        countDownTimer.countDownInSeconds.test {
            countDownTimer.incrementCountDownTimerDuration(4.seconds)
            countDownTimer.startCountDownTimer()
            assertEquals(0, awaitItem()) // initial state is 0
            assertEquals(4, awaitItem())
            assertEquals(3, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(1, awaitItem())
            assertEquals(0, awaitItem())
        }
    }

    @Test
    fun `if countdown timer is stopped no more values are received until the countdown timer is restarted`() = runTest {
        val countDownTimer: ICountDownTimer = MicrowaveCountDownTimer {
            // no op
        }
        countDownTimer.countDownInSeconds.test {
            countDownTimer.incrementCountDownTimerDuration(4.seconds)
            countDownTimer.startCountDownTimer()
            assertEquals(0, awaitItem()) // initial state is 0
            assertEquals(4, awaitItem())
            assertEquals(3, awaitItem())

            countDownTimer.stopCountDownTimer()
            assertEquals(3, countDownTimer.countDownInSeconds.value)
            countDownTimer.startCountDownTimer()

            assertEquals(2, awaitItem())
            assertEquals(1, awaitItem())
            assertEquals(0, awaitItem())

        }
    }

    @Test
    fun `when countdown time reaches zero onFinish callback is called`() = runTest {
        val onFinishedMock = mockk<() -> Unit>()
        every { onFinishedMock.invoke() } returns Unit

        val countDownTimer = MicrowaveCountDownTimer(onFinishedMock)
        countDownTimer.incrementCountDownTimerDuration(1.seconds)
        countDownTimer.startCountDownTimer()

        countDownTimer.countDownInSeconds.test {
            assertEquals(1, awaitItem())
            assertEquals(0, awaitItem())
            verify(exactly = 1) { onFinishedMock.invoke() }
        }
    }
}
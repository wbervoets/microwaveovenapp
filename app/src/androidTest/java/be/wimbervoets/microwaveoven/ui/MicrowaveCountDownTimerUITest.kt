package be.wimbervoets.microwaveoven.ui

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import be.wimbervoets.microwaveoven.controller.timer.MicrowaveCountDownTimer
import org.junit.Rule
import org.junit.Test
import kotlin.time.Duration.Companion.seconds

class MicrowaveCountDownTimerUITest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testMicrowaveCountDownTimerCountsDownToZeroWhenStarted() {
        val microwaveCountDownTimer = MicrowaveCountDownTimer {}
        microwaveCountDownTimer.incrementCountDownTimerDuration(2.seconds)
        composeRule.setContent {
            MicrowaveCountDownTimerUI(timer = microwaveCountDownTimer)
        }

        val timerNode = composeRule.onNodeWithText("sec", substring = true)
        timerNode.assertTextContains("2", substring = true)

        microwaveCountDownTimer.startCountDownTimer()

        composeRule.waitUntil(timeoutMillis = 3000) { microwaveCountDownTimer.countDownInSeconds.value == 0 }

        // here the timer should display 0 sec
        timerNode.assertTextContains("0", substring = true)
    }
}
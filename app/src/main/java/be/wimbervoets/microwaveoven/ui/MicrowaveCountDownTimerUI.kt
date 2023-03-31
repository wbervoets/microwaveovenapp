package be.wimbervoets.microwaveoven.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.wimbervoets.microwaveoven.controller.timer.ICountDownTimer
import be.wimbervoets.microwaveoven.controller.timer.MicrowaveCountDownTimer

@Composable
fun MicrowaveCountDownTimerUI(timer: ICountDownTimer, modifier: Modifier = Modifier) {
    val state: State<Int> = timer.countDownInSeconds.collectAsState()
    Text(
        text = "${state.value} sec",
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.displayMedium,
        modifier = modifier
            .background(Color.Black)
            .border(2.dp, MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    )
}


@Preview
@Composable
fun MicrowaveCountDownTimerUIPreview() {
    val microwaveCountDownTimer = MicrowaveCountDownTimer {
        // on finished no op
    }
    MicrowaveCountDownTimerUI(timer = microwaveCountDownTimer)
    microwaveCountDownTimer.startCountDownTimer()
}
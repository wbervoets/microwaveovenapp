package be.wimbervoets.microwaveoven.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OpenDoorButtonUI(onClick: () -> Unit) {
    Button(
        onClick = onClick) {
        Text(text = "Open / Close Door")
    }
}

@Composable
@Preview
fun OpenDoorButtonUIPreview() {
    OpenDoorButtonUI {
        // on click no op
    }
}
package be.wimbervoets.microwaveoven.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StartButtonUI(onClick: () -> Unit) {
    Button(
        onClick = onClick) {
        Text(text = "START / +60s")
    }
}

@Composable
@Preview
fun StartButtonUIPreview() {
    StartButtonUI {
        // on click no op
    }
}
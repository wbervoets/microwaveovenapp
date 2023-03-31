package be.wimbervoets.microwaveoven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import be.wimbervoets.microwaveoven.ui.MicrowaveUI
import be.wimbervoets.microwaveoven.ui.theme.MicrowaveOvenAppTheme

class MicrowaveActivity : ComponentActivity() {

    private val viewModel: MicrowaveViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MicrowaveOvenAppTheme {
                MicrowaveUI(viewModel)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MicrowaveOvenAppTheme {
        MicrowaveUI(viewModel = MicrowaveViewModel())
    }
}
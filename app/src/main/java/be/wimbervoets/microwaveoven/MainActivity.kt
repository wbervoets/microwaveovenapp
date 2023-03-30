package be.wimbervoets.microwaveoven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import be.wimbervoets.microwaveoven.controller.DFSMicrowave
import be.wimbervoets.microwaveoven.controller.IMicrowave
import be.wimbervoets.microwaveoven.controller.door.Door
import be.wimbervoets.microwaveoven.controller.door.IDoor
import be.wimbervoets.microwaveoven.controller.heater.Heater
import be.wimbervoets.microwaveoven.controller.heater.IHeater
import be.wimbervoets.microwaveoven.controller.light.ILightBulb
import be.wimbervoets.microwaveoven.controller.light.LEDLightBulb
import be.wimbervoets.microwaveoven.ui.MicrowaveUI
import be.wimbervoets.microwaveoven.ui.theme.MicrowaveOvenAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val door: IDoor = Door()
        val heater: IHeater = Heater()
        val lightBulb: ILightBulb = LEDLightBulb()
        val microwave: IMicrowave = DFSMicrowave(door, heater, lightBulb)

        setContent {
            MicrowaveOvenAppTheme {
                MicrowaveUI(microwave)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MicrowaveOvenAppTheme {
        Text(text = "Hello abc!")
    }
}
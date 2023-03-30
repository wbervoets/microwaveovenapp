package be.wimbervoets.microwaveoven.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import be.wimbervoets.microwaveoven.R
import be.wimbervoets.microwaveoven.controller.heater.Heater
import be.wimbervoets.microwaveoven.controller.heater.IHeater

@Composable
fun HeaterUI(heater: IHeater, alignment: Alignment = Alignment.Center) {
    if (heater.isHeating.collectAsState(false).value) {
        Image(
            painterResource(id = R.drawable.ic_heat),
            contentDescription = "Heating..",
            alignment = alignment
        )
    }
}

@Preview
@Composable
fun HeaterisOffUIPreview() {
    val heater = Heater()
    HeaterUI(heater = heater)
}

@Preview
@Composable
fun HeaterisOnUIPreview() {
    val heater = Heater().apply {
        turnOnHeater()
    }
    HeaterUI(heater = heater)
}
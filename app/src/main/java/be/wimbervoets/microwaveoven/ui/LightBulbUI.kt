package be.wimbervoets.microwaveoven.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import be.wimbervoets.microwaveoven.R
import be.wimbervoets.microwaveoven.controller.light.ILightBulb
import be.wimbervoets.microwaveoven.controller.light.LEDLightBulb

@Composable
fun LightBulbUI(lightBulb: ILightBulb, modifier: Modifier = Modifier) {
    val lightBulbState = lightBulb.lightBulbState.collectAsState(initial = false)
    Image(  painter = painterResource(id = R.drawable.ic_lightbulb),
            contentDescription = "Light bulb",
            colorFilter = if (lightBulbState.value) { ColorFilter.tint(Color.Yellow) } else { ColorFilter.tint(Color.Black) },
            modifier = modifier
    )
}

@Preview
@Composable
fun LightBulbOffPreview() {
    val lightBulb = LEDLightBulb()
    LightBulbUI(lightBulb)
}

@Preview
@Composable
fun LightBulbOnPreview() { // looks like preview in Android studio is incorrect but ok when preview is run on device
    val lightBulb = LEDLightBulb()
    lightBulb.turnLightOn()
    LightBulbUI(lightBulb)
}
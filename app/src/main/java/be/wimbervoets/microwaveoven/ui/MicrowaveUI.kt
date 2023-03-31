package be.wimbervoets.microwaveoven.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import be.wimbervoets.microwaveoven.MicrowaveViewModel
import be.wimbervoets.microwaveoven.R


@Composable
fun MicrowaveUI(viewModel: MicrowaveViewModel) {
    val microwave = viewModel.microwave
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
            HeaterUI(heater = microwave.heater)
            Image(painter = painterResource(id = R.drawable.microwave), contentDescription = "Microwave oven")
        }

        MicrowaveCountDownTimerUI(timer = microwave.countDownTimer)

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            StartButtonUI {
                microwave.pressStartButton()
            }
            OpenDoorButtonUI {
                if (microwave.isDoorOpen()) {
                    microwave.closeDoor()
                } else {
                    microwave.openDoor()
                }
            }
        }

       Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            DoorUI(door = microwave.door)
            LightBulbUI(lightBulb = microwave.lightBulb)
        }
    }

}




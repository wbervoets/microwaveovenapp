package be.wimbervoets.microwaveoven.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import be.wimbervoets.microwaveoven.controller.door.Door
import be.wimbervoets.microwaveoven.controller.door.DoorState
import be.wimbervoets.microwaveoven.controller.door.IDoor

@Composable
fun DoorUI(door: IDoor, modifier: Modifier = Modifier) {
    Text(
        text = "Door is ${door.doorStateChanged.collectAsState(initial = DoorState.CLOSED).value.name}",
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Preview
@Composable
fun DoorOpenUIPreview() {
    val door = Door()
    door.openDoor()
    DoorUI(door)
}

@Preview
@Composable
fun DoorClosedUIPreview() {
    val door = Door()
    DoorUI(door)
}
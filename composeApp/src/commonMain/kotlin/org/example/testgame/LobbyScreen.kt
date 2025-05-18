package org.example.testgame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.example.testgame.components.LobbyItem1
import org.example.testgame.components.LobbyItem2
import org.example.testgame.components.LobbyItem3
import org.example.testgame.components.LobbyItem4

@Composable
fun LobbyScreenRoot() {
    var timeLeft by remember { mutableStateOf(3600) }
    var formatedTime by remember { mutableStateOf("") }

    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft--
            formatedTime = formatTimeLeftKmm(timeLeft)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp, vertical = 4.dp
            )
                .verticalScroll(rememberScrollState())
        ) {
            LobbyItem1(
                time = formatedTime
            )
            Spacer(Modifier.height(48.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                LobbyItem2()
                LobbyItem3()
            }
            Spacer(Modifier.height(48.dp))
            LobbyItem4()
        }
    }
}

fun formatTimeLeftKmm(totalSeconds: Int): String {
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return "${hours.pad2()}:${minutes.pad2()}:${seconds.pad2()}"
}

fun Int.pad2(): String = if (this < 10) "0$this" else this.toString()




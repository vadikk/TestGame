package org.example.testgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import testgame.composeapp.generated.resources.Res
import testgame.composeapp.generated.resources.arrow_right
import testgame.composeapp.generated.resources.challenges
import testgame.composeapp.generated.resources.challenges_description
import testgame.composeapp.generated.resources.coming_soon
import testgame.composeapp.generated.resources.dog_contest
import testgame.composeapp.generated.resources.info
import testgame.composeapp.generated.resources.play_now
import testgame.composeapp.generated.resources.refer_code
import testgame.composeapp.generated.resources.refer_text
import testgame.composeapp.generated.resources.skills
import testgame.composeapp.generated.resources.skills_description
import testgame.composeapp.generated.resources.timer

@Composable
fun LobbyItem1(
    time: String = "00:00:00"
) {
    Surface (
        shape = RoundedCornerShape(8),
        color = Color.Green.copy(alpha = 0.2F)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.coming_soon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
                    .padding(bottom = 45.dp)
            )
            Row(
                modifier = Modifier.align(Alignment.TopEnd)
                    .padding(top = 10.dp, end = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = time,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Green,
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    painter = painterResource(Res.drawable.timer),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                    tint = Color.Green
                )
            }
            Text(
                text = stringResource(Res.string.dog_contest),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
                    .padding(bottom = 10.dp, start = 14.dp)
            )
            Row(
                modifier = Modifier.align(Alignment.BottomEnd)
                    .padding(bottom = 10.dp, end = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.play_now),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    painter = painterResource(Res.drawable.arrow_right),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun LobbyItem2() {
    Column(
        modifier = Modifier.widthIn(max = 150.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.challenges),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = stringResource(Res.string.challenges),
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = stringResource(Res.string.challenges_description),
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            ),
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.width(120.dp),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = Color.Green.copy(alpha = 0.2F)
            )
        ) {
            Text(
                text = stringResource(Res.string.play_now),
                style = MaterialTheme.typography.titleSmall,
                color = Color.White,
            )
        }
    }
}

@Composable
fun LobbyItem3() {
    Column(
        modifier = Modifier.widthIn(max = 150.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.skills),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = stringResource(Res.string.skills),
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = stringResource(Res.string.skills_description),
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            ),
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.width(120.dp),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = Color.Green.copy(alpha = 0.2F)
            )
        ) {
            Text(
                text = stringResource(Res.string.play_now),
                style = MaterialTheme.typography.titleSmall,
                color = Color.White,
            )
        }
    }
}

@Composable
fun LobbyItem4() {
    Box (
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(8.dp),
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF91865e), Color(0xFF726124))
                )
            )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.info),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 10.dp, end = 14.dp)
                    .size(20.dp),
                tint = Color.White
            )
            Column(
                modifier = Modifier.padding(top = 40.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.refer_text),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = stringResource(Res.string.refer_code),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 24.sp,
                        fontStyle = FontStyle.Normal
                    ),
                    color = Color.Red
                )
            }
        }
    }
}
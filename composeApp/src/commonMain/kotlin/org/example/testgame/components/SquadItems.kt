package org.example.testgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.compose.dnd.drag.DropStrategy
import com.mohamedrejeb.compose.dnd.drop.dropTarget
import com.mohamedrejeb.compose.dnd.reorder.ReorderState
import com.mohamedrejeb.compose.dnd.reorder.ReorderableItem
import com.mohamedrejeb.compose.dnd.reorder.rememberReorderState
import org.example.testgame.CardItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import testgame.composeapp.generated.resources.Res
import testgame.composeapp.generated.resources.aggression
import testgame.composeapp.generated.resources.boldness
import testgame.composeapp.generated.resources.coin
import testgame.composeapp.generated.resources.guarding
import testgame.composeapp.generated.resources.herding
import testgame.composeapp.generated.resources.hunting
import testgame.composeapp.generated.resources.retrieving
import testgame.composeapp.generated.resources.scent_detection
import testgame.composeapp.generated.resources.timer

@Composable
fun CardItem(
    cardItem: CardItem,
    modifier: Modifier = Modifier
        .padding(8.dp)
        .width(95.dp)
        .height(100.dp),
    detailClick: (cardItem: CardItem) -> Unit = {}
) {
    Surface (
        shape = RoundedCornerShape(8),
        color = Color(0xFFa0c6f7),
    ) {
        Box(
            modifier = modifier
        ) {
            Image(
                painter = painterResource(cardItem.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.aspectRatio(0.65f, true)
                    .align(Alignment.Center)
                    .padding(bottom = 35.dp)
            )
            Text(
                text = cardItem.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                modifier = Modifier.align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp)
            )
        }
    }
}

@Composable
fun CardItemBackSide(
    cardItem: CardItem,
    modifier: Modifier = Modifier
        .padding(8.dp)
        .width(95.dp)
        .height(100.dp)
) {
    Surface (
        shape = RoundedCornerShape(8),
        color = Color(0xFFa0c6f7),
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center
        ) {
            CardDescription(title = stringResource(Res.string.guarding), value = cardItem.guarding)
            CardDescription(title = stringResource(Res.string.herding), value = cardItem.herding)
            CardDescription(title = stringResource(Res.string.hunting), value = cardItem.hunting)
            CardDescription(title = stringResource(Res.string.retrieving), value = cardItem.retrieving)
            CardDescription(title = stringResource(Res.string.scent_detection), value = cardItem.scentDetection)
            CardDescription(title = stringResource(Res.string.boldness), value = cardItem.boldness)
            CardDescription(title = stringResource(Res.string.aggression), value = cardItem.aggression)
        }
    }
}

@Composable
fun CardDescription(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            modifier = Modifier.weight(0.1F)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
        )
    }
}

@Composable
fun CardItemPlaceHolder(
    reorderState: ReorderState<CardItem> = rememberReorderState(),
    key: String,
    cardItem: CardItem,
    applyCardToPitch: (cardItem: CardItem) -> Unit = {}
) {
    Surface (
        shape = RoundedCornerShape(8),
        color = Color(0xFF666563),
        modifier = Modifier
            .dropTarget(
                key = key,
                state = reorderState.dndState,
                dropAnimationEnabled = false,
                onDrop = { state ->
                    applyCardToPitch(state.data)
                },
            )
    ) {
        if (cardItem.id == -1) {
            Box(
                modifier = Modifier.width(112.dp)
                    .height(116.dp)
            ) {
                Text(
                    text = "Dog 1",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.BottomCenter)
                        .padding(bottom = 5.dp)
                )
            }
        } else {
            ReorderableItem<CardItem>(
                state = reorderState,
                key = key,
                data = cardItem,
                zIndex = 1f,
                dropStrategy = DropStrategy.CenterDistance,
                onDrop = { state ->
                    applyCardToPitch(state.data)
                },
                draggableContent = {
                    CardItem(cardItem)
                },
            ) {
                CardItem(cardItem)
            }
        }
    }

}

@Composable
fun SquadItem1(
    time: String = "00:00:00"
) {
    Surface (
        shape = RoundedCornerShape(8),
        color = Color(0xFF91e0d7)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.coin),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(20.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "123",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Red,
                modifier = Modifier
            )
            Spacer(Modifier.weight(0.1F))
            Text(
                text = time,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                painter = painterResource(Res.drawable.timer),
                contentDescription = null,
                modifier = Modifier.size(15.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun PitchPlaceHolder(
    reorderState: ReorderState<CardItem> = rememberReorderState(),
    pitchPlayers: Map<String, CardItem> = emptyMap(),
    applyCardToPitch: (pitchId: String, cardItem: CardItem) -> Unit
) {
    Row (
       modifier = Modifier.fillMaxWidth()
           .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically),

        ) {
            CardItemPlaceHolder(
                reorderState,
                key = "pitch1",
                cardItem = pitchPlayers["pitch1"] ?: CardItem(),
                applyCardToPitch = { applyCardToPitch("pitch1", it) }
            )
            CardItemPlaceHolder(
                reorderState,
                key = "pitch2",
                cardItem = pitchPlayers["pitch2"] ?: CardItem(),
                applyCardToPitch = { applyCardToPitch("pitch2", it) }
            )
        }
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically)
        ) {
            CardItemPlaceHolder(
                reorderState,
                key = "pitch3",
                cardItem = pitchPlayers["pitch3"] ?: CardItem(),
                applyCardToPitch = { applyCardToPitch("pitch3", it) }
            )
            CardItemPlaceHolder(
                reorderState,
                key = "pitch4",
                cardItem = pitchPlayers["pitch4"] ?: CardItem(),
                applyCardToPitch = { applyCardToPitch("pitch4", it) }
            )
            CardItemPlaceHolder(
                reorderState,
                key = "pitch5",
                cardItem = pitchPlayers["pitch5"] ?: CardItem(),
                applyCardToPitch = { applyCardToPitch("pitch5", it) }
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
        ) {
            CardItemPlaceHolder(
                reorderState,
                key = "pitch6",
                cardItem = pitchPlayers["pitch6"] ?: CardItem(),
                applyCardToPitch = { applyCardToPitch("pitch6", it) }
            )
            CardItemPlaceHolder(
                reorderState,
                key = "pitch7",
                cardItem = pitchPlayers["pitch7"] ?: CardItem(),
                applyCardToPitch = { applyCardToPitch("pitch7", it) }
            )
        }
    }
}
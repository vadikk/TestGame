package org.example.testgame

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohamedrejeb.compose.dnd.drag.DropStrategy
import com.mohamedrejeb.compose.dnd.reorder.ReorderContainer
import com.mohamedrejeb.compose.dnd.reorder.ReorderableItem
import com.mohamedrejeb.compose.dnd.reorder.rememberReorderState
import kotlinx.coroutines.delay
import org.example.testgame.components.CardItem
import org.example.testgame.components.PitchPlaceHolder
import org.example.testgame.components.SquadItem1
import org.jetbrains.compose.resources.stringResource
import testgame.composeapp.generated.resources.Res
import testgame.composeapp.generated.resources.dog_contest
import testgame.composeapp.generated.resources.edit

@Composable
fun SquadScreenRoot(
    detailClick: (cardId: Int) -> Unit = {}
) {
    val gameVM = remember { GameVM() }
    val cardList by gameVM.cardItemList.collectAsStateWithLifecycle()

    SquadScreen(
        cardItemList = cardList,
        detailClick = { detailClick(it.id) }
    )
}

@Composable
fun SquadScreen(
    cardItemList: List<CardItem>,
    detailClick: (cardItem: CardItem) -> Unit = {}
) {
    val reorderState = rememberReorderState<CardItem>(dragAfterLongPress = true)
    var pitchPlayers by remember {
        mutableStateOf(
            mapOf<String, CardItem>(
                "pitch1" to CardItem(),
                "pitch2" to CardItem(),
                "pitch3" to CardItem(),
                "pitch4" to CardItem(),
                "pitch5" to CardItem(),
                "pitch6" to CardItem(),
                "pitch7" to CardItem(),
            )
        )
    }
    var playerCard by remember(cardItemList) { mutableStateOf(cardItemList) }
    var timeLeft by remember { mutableStateOf(3600) }
    var formatedTime by remember { mutableStateOf("") }

    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft--
            formatedTime = formatTimeLeftKmm(timeLeft)
        }
    }


    ReorderContainer(
        state = reorderState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF4f3f06))
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(15.dp))
            Text(
                text = stringResource(Res.string.dog_contest),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 22.sp
                ),
                color = Color.White,
            )
            Spacer(Modifier.height(20.dp))
            SquadItem1(time = formatedTime)
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(1F))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.width(120.dp),
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = Color.Green.copy(alpha = 0.2F)
                    )
                ) {
                    Text(
                        text = stringResource(Res.string.edit),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                    )
                }
            }
            Spacer(Modifier.height(30.dp))
            PitchPlaceHolder(
                reorderState,
                pitchPlayers = pitchPlayers,
                applyCardToPitch = { pitchId, card ->

                    val filteredPitchCard = pitchPlayers.getValue(pitchId)
                    if (filteredPitchCard.id == -1) {
                        playerCard = playerCard.toMutableList().apply {
                            remove(card)
                        }
                    } else {
                        if (filteredPitchCard.id != card.id) {
                            val replaceCard = cardItemList.find { it.id == filteredPitchCard.id }
                            playerCard = playerCard.toMutableList().apply {
                                if (replaceCard != null) add(replaceCard)
                                remove(card)
                            }.sortedBy { it.id }
                        }
                    }

                    pitchPlayers = pitchPlayers.toMutableMap().apply {
                        this[pitchId] = card
                    }
                }
            )
            Spacer(Modifier.weight(0.1F))
            LazyRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(
                    items = playerCard,
                    key = { it.id }
                ) { card ->
                    ReorderableItem(
                        state = reorderState,
                        key = card,
                        data = card,
                        dropStrategy = DropStrategy.CenterDistance,
                        onDrop = { state ->
                            val index = playerCard.indexOf(state.data)
                            if (index != -1) return@ReorderableItem

                            playerCard = playerCard.toMutableList().apply {
                                add(state.data)
                            }.sortedBy { it.id }

                            val filteredPitchCard = pitchPlayers.filterValues { it == state.data }
                            pitchPlayers = pitchPlayers.toMutableMap().apply {
                                this[filteredPitchCard.keys.first()] = CardItem()
                            }

                        },
                        draggableContent = {
                            CardItem(
                                cardItem = card
                            )

                        },
                    ) {
                        Box(
                            modifier = Modifier.clickable { detailClick(card) }
                        ) {
                            CardItem(
                                cardItem = card
                            )
                        }
                    }
                }
            }
        }
    }
}
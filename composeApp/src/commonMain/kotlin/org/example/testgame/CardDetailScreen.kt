package org.example.testgame

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.testgame.components.CardItem
import org.example.testgame.components.CardItemBackSide
import org.example.testgame.components.DotsIndicator
import org.jetbrains.compose.resources.painterResource
import testgame.composeapp.generated.resources.Res
import testgame.composeapp.generated.resources.cancel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardDetailScreen(
    cardId: Int,
    close: () -> Unit = {}
) {
    val gameVM = remember { GameVM() }
    val cardList by gameVM.cardItemList.collectAsStateWithLifecycle()

    val cardPosition by remember(cardList.size) {
        mutableStateOf(
            cardList.indexOf(cardList.find { it.id == cardId } ?: 0)
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(Res.drawable.cancel),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp)
                .size(30.dp)
                .clickable { close() },
            colorFilter = ColorFilter.tint(Color.White)
        )

        if (cardPosition != -1) {
            val pagerState = rememberPagerState(
                initialPage = cardPosition,
                pageCount = { cardList.size },
            )

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                ) { page ->

                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .height(300.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        FlipCard(
                            cardItem = cardList.getOrNull(page) ?: CardItem()
                        )
                    }
                }

                DotsIndicator(
                    totalDots = cardList.size,
                    selectedIndex = pagerState.currentPage,
                    modifier = Modifier.padding(top = 24.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlipCard(
    cardItem: CardItem
) {
    var flipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (flipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        ),
        label = ""
    )

    Card(
        elevation = 1.dp,
        backgroundColor = Color.Transparent,
        border = BorderStroke(0.dp, color = Color.Transparent),
        modifier = Modifier
            .width(250.dp)
            .height(300.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
        ,
        onClick = { flipped = !flipped }
    ) {
        Box {
            if (rotation <= 90) {
                CardItem(
                    cardItem = cardItem,
                    modifier = Modifier
                        .padding(8.dp)
                        .width(250.dp)
                        .height(300.dp)
                )
            } else {
                Box(
                    Modifier.graphicsLayer {
                        rotationY = 180f
                    }
                ) {
                    CardItemBackSide(
                        cardItem = cardItem,
                        modifier = Modifier
                            .padding(8.dp)
                            .width(250.dp)
                            .height(300.dp)
                    )
                }
            }

        }
    }
}
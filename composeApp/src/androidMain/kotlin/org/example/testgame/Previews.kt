package org.example.testgame

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.testgame.components.CardDescription
import org.example.testgame.components.LobbyItem1
import org.example.testgame.components.LobbyItem2
import org.example.testgame.components.LobbyItem3
import org.example.testgame.components.LobbyItem4
import org.example.testgame.components.CardItem
import org.example.testgame.components.CardItemBackSide
import org.example.testgame.components.SquadItem1
import org.example.testgame.components.CardItemPlaceHolder
import org.example.testgame.components.DotsIndicator
import org.example.testgame.components.PitchPlaceHolder
import org.example.testgame.navigation.CustomBottomBar
import testgame.composeapp.generated.resources.Res
import testgame.composeapp.generated.resources.dog1

@Preview
@Composable
private fun LobbyScreenRootPreview() {
    MaterialTheme {
        LobbyScreenRoot()
    }
}

@Preview
@Composable
private fun LobbyItem1Preview() {
    MaterialTheme {
        LobbyItem1()
    }
}

@Preview
@Composable
private fun LobbyItem2Preview() {
    MaterialTheme {
        LobbyItem2()
    }
}

@Preview
@Composable
private fun LobbyItem3Preview() {
    MaterialTheme {
        LobbyItem3()
    }
}

@Preview
@Composable
private fun LobbyItem4Preview() {
    MaterialTheme {
        LobbyItem4()
    }
}

@Preview
@Composable
private fun CustomBottomBarPreview() {
    MaterialTheme {
        CustomBottomBar()
    }
}

@Preview
@Composable
private fun CardItemPreview() {
    MaterialTheme {
        CardItem(
            CardItem(
                id = 1,
                image = Res.drawable.dog1,
                name = "Dog 1"
            )
        )
    }
}

@Preview
@Composable
private fun CardItemPlaceHolderPreview() {
    MaterialTheme {
        CardItemPlaceHolder(
            key = "pitch1",
            cardItem = CardItem()
        )
    }
}

@Preview
@Composable
private fun SquadItem1Preview() {
    MaterialTheme {
        SquadItem1()
    }
}

@Preview
@Composable
private fun PitchPlaceHolderPreview() {
    MaterialTheme {
        PitchPlaceHolder(
            applyCardToPitch = {_, _ -> }
        )
    }
}

@Preview
@Composable
private fun SquadScreenPreview() {
    MaterialTheme {
        SquadScreen(
            cardItemList = List(10) { index ->
                CardItem(
                    id = index,
                    image =  drawableMap[index] ?: Res.drawable.dog1,
                    name = "Dog ${index + 1}"
                )
            },
        )
    }
}

@Preview
@Composable
private fun CardDetailScreenPreview() {
    MaterialTheme{
        CardDetailScreen(
            cardId = 8
        )
    }
}

@Preview
@Composable
private fun DotsIndicatorPreview() {
    MaterialTheme{
        DotsIndicator(
           totalDots = 10,
            selectedIndex = 3
        )
    }
}

@Preview
@Composable
private fun CardDescriptionPreview() {
    MaterialTheme{
        CardDescription(
            title = "Guarding",
            value = "7.3"
        )
    }
}

@Preview
@Composable
private fun CardItemBackSidePreview() {
    MaterialTheme{
        CardItemBackSide(
            cardItem = CardItem(),
            modifier = Modifier
                .padding(8.dp)
                .width(250.dp)
                .height(300.dp)
        )
    }
}
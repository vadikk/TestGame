package org.example.testgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import testgame.composeapp.generated.resources.Res
import testgame.composeapp.generated.resources.dog1
import testgame.composeapp.generated.resources.dog2
import testgame.composeapp.generated.resources.dog3
import testgame.composeapp.generated.resources.dog4
import testgame.composeapp.generated.resources.dog5
import testgame.composeapp.generated.resources.dog6
import testgame.composeapp.generated.resources.dog7
import testgame.composeapp.generated.resources.dog8
import testgame.composeapp.generated.resources.dog9
import testgame.composeapp.generated.resources.dog10
import kotlin.math.round
import kotlin.random.Random

class GameVM: ViewModel() {

    private val _cardItemList = MutableStateFlow<List<CardItem>>(emptyList())
    val cardItemList: StateFlow<List<CardItem>> = _cardItemList
        .onStart {
            generateCardList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _cardItemList.value
        )

    fun deleteCard(id: Int) {
        viewModelScope.launch {
            _cardItemList.update { cards ->
                val newCardList = cards.toMutableList()
                val findCard = newCardList.find { it.id == id }
                val findCardIndex = newCardList.indexOf(findCard)
                newCardList.removeAt(findCardIndex)

                newCardList
            }
        }
    }

    private fun generateCardList() {
        val generatedList =  List(10) { index ->
            CardItem(
                id = index + 1,
                image =  drawableMap[index + 1] ?: Res.drawable.dog1,
                name = "Dog ${index + 1}",
                guarding = "${roundTo1Decimal(Random.nextDouble(1.0, 10.0))}",
                herding = "${roundTo1Decimal(Random.nextDouble(1.0, 10.0))}",
                hunting = "${roundTo1Decimal(Random.nextDouble(1.0, 10.0))}",
                retrieving = "${roundTo1Decimal(Random.nextDouble(1.0, 10.0))}",
                scentDetection = "${roundTo1Decimal(Random.nextDouble(1.0, 10.0))}",
                boldness = "${roundTo1Decimal(Random.nextDouble(1.0, 10.0))}",
                aggression = "${roundTo1Decimal(Random.nextDouble(1.0, 10.0))}",
            )
        }
        viewModelScope.launch {
            _cardItemList.update {
                generatedList
            }
        }
    }

    private fun roundTo1Decimal(value: Double): Double {
        return round(value * 10) / 10
    }
}

data class CardItem(
    val id: Int = -1,
    val image: DrawableResource = Res.drawable.dog1,
    val name: String = "",
    val guarding: String = "",
    val herding: String = "",
    val hunting: String = "",
    val retrieving: String = "",
    val scentDetection: String = "",
    val boldness: String = "",
    val aggression: String = ""
)

val drawableMap = mapOf(
    1 to Res.drawable.dog1,
    2 to Res.drawable.dog2,
    3 to Res.drawable.dog3,
    4 to Res.drawable.dog4,
    5 to Res.drawable.dog5,
    6 to Res.drawable.dog6,
    7 to Res.drawable.dog7,
    8 to Res.drawable.dog8,
    9 to Res.drawable.dog9,
    10 to Res.drawable.dog10
)
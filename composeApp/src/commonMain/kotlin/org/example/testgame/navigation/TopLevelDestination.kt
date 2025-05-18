package org.example.testgame.navigation

import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import testgame.composeapp.generated.resources.Res
import testgame.composeapp.generated.resources.home
import testgame.composeapp.generated.resources.squad

enum class TopLevelDestination(
    val selectedIcon: DrawableResource,
    val route: Route,
    val titleTextId: StringResource
) {
    HOME(
        selectedIcon = Res.drawable.home,
        route = Home,
        titleTextId = Res.string.home,
    ),
    SQUAD(
        selectedIcon = Res.drawable.squad,
        route = Squad,
        titleTextId = Res.string.squad,
    )
}

@Serializable
data object Home: Route

@Serializable
data object Squad: Route

@Serializable
data class CardDetail(val cardId: Int): Route

sealed interface Route
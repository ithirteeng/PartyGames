package com.ith.partygames.screens.common_connection.host.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ith.partygames.common.games.GameType
import com.ith.partygames.screens.common_connection.host.ui.CommonConnectionHostScreen
import kotlinx.serialization.Serializable

@Serializable
data class CommonConnectionHostRoute(
    val gameType: GameType,
)

fun NavController.navigateToCommonConnectionHostScreen(
    gameType: GameType,
    navOptions: NavOptions? = null,
) {
    navigate(
        route = CommonConnectionHostRoute(gameType = gameType),
        navOptions = navOptions
    )
}

internal fun NavGraphBuilder.hostScreen() {
    composable<CommonConnectionHostRoute> {
        CommonConnectionHostScreen()
    }
}


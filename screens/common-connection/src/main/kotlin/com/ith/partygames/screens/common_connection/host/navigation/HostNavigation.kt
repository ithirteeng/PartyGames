package com.ith.partygames.screens.common_connection.host.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ith.partygames.common.games.GameType
import com.ith.partygames.screens.common_connection.host.ui.HostScreen
import kotlinx.serialization.Serializable

@Serializable
internal data class HostRoute(
    val gameType: GameType,
)

internal fun NavController.navigateToHostScreen(
    gameType: GameType,
    navOptions: NavOptions? = null,
) {
    navigate(
        route = HostRoute(gameType = gameType),
        navOptions = navOptions
    )
}

internal fun NavGraphBuilder.hostScreen() {
    composable<HostRoute> {
        HostScreen()
    }
}


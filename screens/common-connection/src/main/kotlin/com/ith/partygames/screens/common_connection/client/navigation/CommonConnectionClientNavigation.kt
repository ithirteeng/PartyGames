package com.ith.partygames.screens.common_connection.client.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ith.partygames.common.games.GameType
import com.ith.partygames.screens.common_connection.client.ui.CommonConnectionClientScreen
import kotlinx.serialization.Serializable

@Serializable
internal data class CommonConnectionClientRoute(
    val gameType: GameType,
)

internal fun NavController.navigateToClientScreen(
    gameType: GameType,
    navOptions: NavOptions? = null,
) {
    navigate(
        route = CommonConnectionClientRoute(gameType = gameType),
        navOptions = navOptions
    )
}

internal fun NavGraphBuilder.clientScreen() {
    composable<CommonConnectionClientRoute> {
        CommonConnectionClientScreen()
    }
}

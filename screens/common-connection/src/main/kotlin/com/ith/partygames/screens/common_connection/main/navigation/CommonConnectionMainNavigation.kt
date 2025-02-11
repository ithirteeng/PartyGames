package com.ith.partygames.screens.common_connection.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ith.partygames.common.games.GameType
import com.ith.partygames.screens.common_connection.main.ui.CommonConnectionMainScreen
import kotlinx.serialization.Serializable

@Serializable
internal data class CommonConnectionMainRoute(
    val gameType: GameType,
)

fun NavController.navigateToCommonConnectionMainScreen(
    gameType: GameType,
    navOptions: NavOptions? = null,
) {
    navigate(
        route = CommonConnectionMainRoute(gameType = gameType),
        navOptions = navOptions
    )
}

internal fun NavGraphBuilder.mainScreen(
    navigateToCommonConnectionHostScreen: (gameType: GameType) -> Unit,
    navigateToCommonConnectionClientScreen: (gameType: GameType) -> Unit,
) {
    composable<CommonConnectionMainRoute> {
        CommonConnectionMainScreen(
            onBecomeHostButtonClick = navigateToCommonConnectionHostScreen,
            onBecomeClientButtonClick = navigateToCommonConnectionClientScreen
        )
    }
}

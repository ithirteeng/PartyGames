package com.ith.partygames.screens.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ith.partygames.common.games.GameType
import com.ith.partygames.screens.main.ui.MainScreen
import kotlinx.serialization.Serializable

@Serializable
object MainRoute

fun NavController.navigateToMainScreen(
    navOptions: NavOptions? = null
) {
    navigate(MainRoute, navOptions)
}

fun NavGraphBuilder.mainScreen(
    navigateToCommonConnectionScreen: (GameType) -> Unit,
) {
    composable<MainRoute> {
        MainScreen(
            navigateToCommonConnectionScreen = navigateToCommonConnectionScreen
        )
    }
}

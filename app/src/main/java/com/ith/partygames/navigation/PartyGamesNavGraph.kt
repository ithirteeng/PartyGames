package com.ith.partygames.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.ith.partygames.screens.common_connection.common.navigation.commonConnectionScreensGraph
import com.ith.partygames.screens.common_connection.main.navigation.navigateToCommonConnectionMainScreen
import com.ith.partygames.screens.main.navigation.MainRoute
import com.ith.partygames.screens.main.navigation.mainScreen

@Composable
fun PartyGamesNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MainRoute
    ) {
        mainScreen(
            navigateToCommonConnectionScreen = { gameType ->
                navController.navigateToCommonConnectionMainScreen(
                    gameType = gameType,
                    navOptions = navOptions {  }
                )
            }
        )

        commonConnectionScreensGraph()
    }
}
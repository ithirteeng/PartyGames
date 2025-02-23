package com.ith.partygames.screens.common_connection.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.ith.partygames.screens.common_connection.client.navigation.clientScreen
import com.ith.partygames.screens.common_connection.client.navigation.navigateToClientScreen
import com.ith.partygames.screens.common_connection.host.navigation.hostScreen
import com.ith.partygames.screens.common_connection.host.navigation.navigateToHostScreen
import com.ith.partygames.screens.common_connection.main.navigation.mainScreen

fun NavGraphBuilder.commonConnectionScreensGraph(
    navController: NavController,
) {

    mainScreen(
        navigateToClientScreen = navController::navigateToClientScreen,
        navigateToHostScreen = navController::navigateToHostScreen,
    )
    hostScreen()
    clientScreen()
}

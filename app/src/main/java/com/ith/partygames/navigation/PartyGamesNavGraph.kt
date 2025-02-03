package com.ith.partygames.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ith.partygames.screens.main.navigation.MainScreen
import com.ith.partygames.screens.main.presentation.MainScreen

@Composable
fun PartyGamesNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen
    ) {
        composable<MainScreen> {
            MainScreen()
        }
    }
}
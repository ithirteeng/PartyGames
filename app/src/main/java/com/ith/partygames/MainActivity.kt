package com.ith.partygames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ith.partygames.common.server.NodeServer
import com.ith.partygames.common.ui.theme.PartyGamesTheme
import com.ith.partygames.navigation.PartyGamesNavGraph
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val nodeServer: NodeServer by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nodeServer.start()
        setContent {
            val systemUiController: SystemUiController = rememberSystemUiController()
            systemUiController.isSystemBarsVisible = false
            PartyGamesTheme {
                PartyGamesNavGraph()
            }
        }
    }
}

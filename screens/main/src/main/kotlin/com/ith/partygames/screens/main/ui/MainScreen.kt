package com.ith.partygames.screens.main.ui

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ith.partygames.common.games.GameType
import com.ith.partygames.screens.main.presentation.MainEvent
import com.ith.partygames.screens.main.presentation.MainViewModel
import com.ith.partygames.screens.main.utils.NEARBY_WIFI_PERMISSION_NAME
import com.ith.partygames.screens.main.utils.hasBluetoothConnectPermission
import com.ith.partygames.screens.main.utils.hasNearbyWifiDevicesOrLocationPermission
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel(),
    navigateToCommonConnectionScreen: (GameType) -> Unit,
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsState().value

    val requestBluetoothPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        viewModel.processEvent(MainEvent.BluetoothPermissionGranted(granted))
    }

    val requestNearbyWifiPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (context.hasBluetoothConnectPermission()) {
            viewModel.processEvent(MainEvent.WifiPermissionGranted(granted))
        } else if (Build.VERSION.SDK_INT >= 31) {
            requestBluetoothPermission.launch(Manifest.permission.BLUETOOTH_CONNECT)
        }
    }
    LaunchedEffect(null) {
        if (!context.hasNearbyWifiDevicesOrLocationPermission()) {
            requestNearbyWifiPermissionLauncher.launch(NEARBY_WIFI_PERMISSION_NAME)
        } else if (!context.hasBluetoothConnectPermission() && Build.VERSION.SDK_INT >= 31) {
            requestBluetoothPermission.launch(Manifest.permission.BLUETOOTH_CONNECT)
        }

        if (context.hasNearbyWifiDevicesOrLocationPermission()) {
            viewModel.processEvent(MainEvent.WifiPermissionGranted(true))
        }

        if (context.hasBluetoothConnectPermission() || Build.VERSION.SDK_INT < 31) {
            viewModel.processEvent(MainEvent.BluetoothPermissionGranted(true))
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp)
    ) {
        item {
            if (!(state.isWifiPermissionGranted && state.isBluetoothPermissionGranted)) {
                Text("You need to grant all permissions at first")
            }
        }
        items(state.games) { gameType ->
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navigateToCommonConnectionScreen(gameType) },
                enabled = state.isWifiPermissionGranted && state.isBluetoothPermissionGranted
            ) {
                Text(text = gameType.name)
            }
        }
    }
}

package com.ith.partygames.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object PartyGamesTheme {
    val colors: PartyGamesColors
        @ReadOnlyComposable
        @Composable
        get() = LocalColors.current

    val materialColors: ColorScheme
        @ReadOnlyComposable
        @Composable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @ReadOnlyComposable
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @ReadOnlyComposable
        @Composable
        get() = MaterialTheme.shapes
}

@Composable
fun PartyGamesTheme(
    isSystemInDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkMode) darkColorsScheme else lightColorsScheme
    CompositionLocalProvider(LocalColors provides colors) {
        content()
    }
}

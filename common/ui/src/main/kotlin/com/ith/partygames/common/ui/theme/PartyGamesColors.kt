package com.ith.partygames.common.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val red = Color(0xFFFF0000)
val pink = Color(0xFFFFC0CB)
val purple = Color(0xFF800080)
val deepPurple = Color(0xFF673AB7)
val indigo = Color(0xFF3F51B5)
val blue = Color(0xFF2196F3)
val lightBlue = Color(0xFF03A9F4)
val cyan = Color(0xFF00BCD4)
val teal = Color(0xFF009688)
val green = Color(0xFF4CAF50)
val lightGreen = Color(0xFF8BC34A)
val lime = Color(0xFFCDDC39)
val yellow = Color(0xFFFFEB3B)
val amber = Color(0xFFFFC107)
val orange = Color(0xFFFF9800)
val deepOrange = Color(0xFFFF5722)
val brown = Color(0xFF795548)
val grey = Color(0xFF9E9E9E)
val blueGrey = Color(0xFF607D8B)
val black = Color(0xFF000000)

@Immutable
data class PartyGamesColors(
    val red: Color,
    val pink: Color,
    val purple: Color,
    val deepPurple: Color,
    val indigo: Color,
    val blue: Color,
    val lightBlue: Color,
    val cyan: Color,
    val teal: Color,
    val green: Color,
    val lightGreen: Color,
    val lime: Color,
    val yellow: Color,
    val amber: Color,
    val orange: Color,
    val deepOrange: Color,
    val brown: Color,
    val grey: Color,
    val blueGrey: Color,
    val black: Color,
)

val lightColorsScheme = PartyGamesColors(
    red = red,
    pink = pink,
    purple = purple,
    deepPurple = deepPurple,
    indigo = indigo,
    blue = blue,
    lightBlue = lightBlue,
    cyan = cyan,
    teal = teal,
    green = green,
    lightGreen = lightGreen,
    lime = lime,
    yellow = yellow,
    amber = amber,
    orange = orange,
    deepOrange = deepOrange,
    brown = brown,
    grey = grey,
    blueGrey = blueGrey,
    black = black
)

val darkColorsScheme = PartyGamesColors(
    red = red,
    pink = pink,
    purple = purple,
    deepPurple = deepPurple,
    indigo = indigo,
    blue = blue,
    lightBlue = lightBlue,
    cyan = cyan,
    teal = teal,
    green = green,
    lightGreen = lightGreen,
    lime = lime,
    yellow = yellow,
    amber = amber,
    orange = orange,
    deepOrange = deepOrange,
    brown = brown,
    grey = grey,
    blueGrey = blueGrey,
    black = black
)

val LocalColors = staticCompositionLocalOf { lightColorsScheme }
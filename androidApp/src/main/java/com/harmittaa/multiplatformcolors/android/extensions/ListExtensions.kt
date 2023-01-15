package com.harmittaa.multiplatformcolors.android.extensions

import androidx.compose.ui.graphics.Color

fun List<Int>.toColor(): Color {
    return Color(
        red = this[0],
        green = this[1],
        blue = this[2]
    )
}

fun List<Int>.toHexString(): String {
    return Integer.toHexString(android.graphics.Color.rgb(this[0], this[1], this[2]))
}

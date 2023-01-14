package com.harmittaa.multiplatformcolors.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.harmittaa.multiplatformcolors.viewmodel.ColorScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ColorScreen(
    viewModel: ColorScreenViewModel = getViewModel()
) {
    val viewState by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp)
    ) {
        Column {
            Text("Current: ${viewState.currentTemplate}")

            viewState.colorTemplate.forEach { color ->
                ColorBar(
                    color = color,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(vertical = 6.dp)
                )
            }
        }

        Column(Modifier.padding(top = 24.dp)) {
            viewState.colorModelNames.forEach { name ->
                Button(onClick = { viewModel.onTemplateSelected(name) }) {
                    Text(text = name)
                }
            }
        }
    }
}

@Composable
fun ColorBar(
    color: List<Int>,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = modifier.then(
                Modifier
                    .background(
                        color.toColor(),
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        )

        Text(
            text = "#${color.toHexString()}".uppercase(),
            modifier = Modifier
                .rotate(-90f)
        )
    }
}

/*
@Preview
@Composable
fun PreviewColorScreen() {
    MyApplicationTheme {
        val colors = listOf(
            listOf(14, 110, 121),
            listOf(243, 207, 145),
            listOf(215, 192, 82),
            listOf(173, 88, 75),
            listOf(150, 50, 64)
        )
        ColorScreen(
            currentTemplate = "SomeColor",
            colors = colors,
            templates = listOf("one", "two", "three"),
            onTemplateClicked = {}
        )
    }
}

@Preview
@Composable
fun PreviewColorBar() {
    MyApplicationTheme {
        val colors = listOf(
            listOf(14, 110, 121),
            listOf(243, 207, 145),
            listOf(215, 192, 82),
            listOf(173, 88, 75),
            listOf(150, 50, 64)
        )
        ColorBar(
            colors.first()
        )
    }
}
 */
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

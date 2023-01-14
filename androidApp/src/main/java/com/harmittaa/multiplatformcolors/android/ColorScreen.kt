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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.harmittaa.multiplatformcolors.viewmodel.ColorScreenViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun ColorScreen(
    viewModel: ColorScreenViewModel = getViewModel()
) {
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("Loading") }
    var templates by remember { mutableStateOf(emptyList<String>()) }
    var colors by remember { mutableStateOf(emptyList<List<Int>>()) }

    LaunchedEffect(true) {
        scope.launch {
            try {
                val result = viewModel.getColorModels()
                text = result.toString()
                colors = result.first { it.colors.isNotEmpty() }.colors
                templates = result.map { it.name }
            } catch (e: Exception) {
                text = e.localizedMessage ?: "error"
            }
        }
    }

    var currentTemplate by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(key1 = currentTemplate) {
        if (currentTemplate == null) return@LaunchedEffect
        scope.launch {
            val result = viewModel.getAColor(currentTemplate!!)
            colors = result
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp)
    ) {
        Column {
            Text("Current: $currentTemplate")

            colors.forEach { color ->
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
            templates.forEach { template ->
                Button(onClick = { currentTemplate = template }) {
                    Text(text = template)
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

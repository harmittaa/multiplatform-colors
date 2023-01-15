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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harmittaa.multiplatformcolors.android.extensions.toColor
import com.harmittaa.multiplatformcolors.android.extensions.toHexString
import com.harmittaa.multiplatformcolors.viewmodel.ColorScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ColorScreen(
    viewModel: ColorScreenViewModel = getViewModel(),
) {
    val viewState by viewModel.state.collectAsState()

    ColorScreenContent(
        currentTemplateName = viewState.currentTemplateName ?: "",
        colorTemplate = viewState.colorTemplate,
        allTemplates = viewState.colorTemplateNames,
        onTemplateSelected = viewModel::onTemplateSelected,
        onNextTemplateClicked = viewModel::onNextTemplateClicked,
    )
}

@Composable
internal fun ColorScreenContent(
    currentTemplateName: String,
    colorTemplate: List<List<Int>>,
    allTemplates: List<String>,
    onTemplateSelected: (String) -> Unit,
    onNextTemplateClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Text("Current: $currentTemplateName")

            colorTemplate.forEach { color ->
                ColorBar(
                    color = color,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(vertical = 6.dp, horizontal = 12.dp)
                )
            }
            
            Button(
                onClick = onNextTemplateClicked
            ) {
                Text("Next template")
            }
        }

        Column(Modifier.padding(top = 24.dp)) {
            allTemplates.forEach { name ->
                Button(onClick = { onTemplateSelected(name) }) {
                    Text(text = name)
                }
            }
        }
    }
}

@Composable
fun ColorBar(
    color: List<Int>,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = modifier.then(
                Modifier
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(
                        color.toColor(),
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        )

        Text(
            text = "#${color.toHexString()}".uppercase(),
            color = color.toColor(),
            modifier = Modifier
                .vertical()
                .rotate(-90f)
        )
    }
}

// from: https://stackoverflow.com/a/70058688
fun Modifier.vertical() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.height, placeable.width) {
        placeable.place(
            x = -(placeable.width / 2 - placeable.height / 2),
            y = -(placeable.height / 2 - placeable.width / 2)
        )
    }
}

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
        ColorScreenContent(
            currentTemplateName = "SomeColor",
            colorTemplate = colors,
            allTemplates = listOf("one", "two", "three"),
            onTemplateSelected = {},
            onNextTemplateClicked = {},
        )
    }
}

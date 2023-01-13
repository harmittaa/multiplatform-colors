package com.harmittaa.multiplatformcolors.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harmittaa.multiplatformcolors.repository.ColorRepository
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {
    val colorRepository: ColorRepository by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scope = rememberCoroutineScope()
                    var text by remember { mutableStateOf("Loading") }
                    var buttons by remember { mutableStateOf(emptyList<String>()) }
                    var colors by remember { mutableStateOf(emptyList<List<Int>>()) }

                    LaunchedEffect(true) {
                        scope.launch {
                            try {
                                val result = colorRepository.getColorModels()
                                text = result.toString()
                                colors = result.first { it.colors.isNotEmpty() }.colors
                                buttons = result.map { it.name }
                            } catch (e: Exception) {
                                text = e.localizedMessage ?: "error"
                            }
                        }
                    }

                    var selectedOne by remember { mutableStateOf<String?>(null) }
                    LaunchedEffect(key1 = selectedOne) {
                        if (selectedOne == null) return@LaunchedEffect
                        scope.launch {
                            val result = colorRepository.getAColor(selectedOne!!)
                            colors = result
                        }
                    }

                    ColorScreen(
                        /* currentTemplate = selectedOne ?: "",
                        templates = buttons,
                        colors = colors,
                        onTemplateClicked = { selectedOne = it } */
                    )
                    /* Column {
                        Greeting(text)
                        buttons.forEach {
                            Button(onClick = { selectedOne = it }) {
                                Text(text = it)
                            }
                        }

                        colors.forEach {
                            Box(
                                modifier = Modifier
                                    .background(
                                        Color(
                                            red = it[0],
                                            green = it[1],
                                            blue = it[2]
                                        )
                                    )
                                    .size(20.dp)
                            )
                        }
                    } */
                }
            }
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Hello, Android!")
    }
}

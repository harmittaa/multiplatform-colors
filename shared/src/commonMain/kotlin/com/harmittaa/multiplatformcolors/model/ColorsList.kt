package com.harmittaa.multiplatformcolors.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ColorsList(
    @SerialName("result")
    val colors: List<String>
)

@Serializable
data class ColorModel(
    val result: List<List<Int>>
)

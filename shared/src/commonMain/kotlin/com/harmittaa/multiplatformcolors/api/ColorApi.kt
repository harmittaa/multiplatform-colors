package com.harmittaa.multiplatformcolors.api

import com.harmittaa.multiplatformcolors.model.ColorModel
import com.harmittaa.multiplatformcolors.model.ColorsList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable

interface ColorApi {
    suspend fun getColorModelList(): List<String>
    suspend fun getColorModel(model: String): List<List<Int>>
}

@Serializable
data class OutgoingModel(
    val model: String
)

internal class ColorApiImpl(
    private val client: HttpClient
) : ColorApi {
    private val colorListUrl = "http://colormind.io/list/"
    private val colorModelUrl = "http://colormind.io/api/"

    override suspend fun getColorModelList(): List<String> {
        val response = client.get(colorListUrl)
        return response.body<ColorsList>().colors
    }

    override suspend fun getColorModel(model: String): List<List<Int>> {
        val response = client.post(colorModelUrl) {
            contentType(ContentType.Application.Json)
            setBody(OutgoingModel(model = model))
        }
        return response.body<ColorModel>().result
    }
}

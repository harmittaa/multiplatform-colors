package com.harmittaa.multipaltformcolors.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun getNetworkClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            val format = Json {
                prettyPrint = true
                encodeDefaults = true
            }

            // colormind API returns JSON with text/html content type, hence this workaround
            register(ContentType.Text.Html, KotlinxSerializationConverter(format))
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}

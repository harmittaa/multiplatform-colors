package com.harmittaa.multipaltformcolors

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class Greeting(
    private val client: HttpClient
) {

    suspend fun getGreeting(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}

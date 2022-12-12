package com.harmittaa.multipaltformcolors.di

import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {
    single { HttpClient() }
}

package com.harmittaa.multipaltformcolors.di

import appModule
import com.harmittaa.multipaltformcolors.Greeting
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class GreetingHelper : KoinComponent {
    private val greeting: Greeting by inject()
    suspend fun getGreeting(): String = greeting.getGreeting()
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}

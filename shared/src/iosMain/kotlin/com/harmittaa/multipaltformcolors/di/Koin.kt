package com.harmittaa.multipaltformcolors.di

import appModule
import com.harmittaa.multipaltformcolors.repository.ColorRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class GreetingHelper : KoinComponent {
    private val colorRepository: ColorRepository by inject()
    suspend fun getGreeting(): List<String> = colorRepository.getColorModels()
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}

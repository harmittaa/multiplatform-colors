package com.harmittaa.multipaltformcolors.di

import appModule
import com.harmittaa.multipaltformcolors.repository.ColorRepository
import com.harmittaa.multipaltformcolors.repository.ModelWithData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class GreetingHelper : KoinComponent {
    private val colorRepository: ColorRepository by inject()
    suspend fun getColorModels(): List<ModelWithData> = colorRepository.getColorModels()
    suspend fun getAColor(modelName: String): List<List<Int>> = colorRepository.getAColor(model = modelName)
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}

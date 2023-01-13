package com.harmittaa.multiplatformcolors.viewmodel

import com.harmittaa.multiplatformcolors.repository.ColorRepository
import com.harmittaa.multiplatformcolors.repository.ModelWithData

class ColorScreenViewModel(
    private val repository: ColorRepository,
) : BaseViewModel() {


    suspend fun getColorModels(): List<ModelWithData> {
        return repository.getColorModels()
    }

    suspend fun getAColor(model: String): List<List<Int>> {
        return repository.getAColor(model)
    }
}
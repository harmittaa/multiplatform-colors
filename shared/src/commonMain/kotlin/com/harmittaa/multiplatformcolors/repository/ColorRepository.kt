package com.harmittaa.multiplatformcolors.repository

import com.harmittaa.multiplatformcolors.api.ColorApi

interface ColorRepository {
    suspend fun getColorModels(): List<ModelWithData>
    suspend fun getAColor(model: String): List<List<Int>>
}

data class ModelWithData(
    val name: String,
    val colors: List<List<Int>> = emptyList()
)

class ColorRepositoryImpl(
    private val api: ColorApi
) : ColorRepository {

    override suspend fun getColorModels(): List<ModelWithData> {
        val models = api.getColorModelList()
        val chosenModel = models.last()
        val aModelWithData = getAColor(chosenModel)

        val variedList = models.map { ModelWithData(name = it) }
        val indexOfChosen = variedList.indexOfFirst { it.name == chosenModel }
        val mutableList = variedList.toMutableList()
        mutableList[indexOfChosen] = ModelWithData(name = chosenModel, colors = aModelWithData)

        return mutableList
    }

    override suspend fun getAColor(model: String): List<List<Int>> {
        return api.getColorModel(model = model)
    }
}

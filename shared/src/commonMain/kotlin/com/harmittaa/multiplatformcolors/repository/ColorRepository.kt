package com.harmittaa.multiplatformcolors.repository

import com.harmittaa.multiplatformcolors.api.ColorApi

interface ColorRepository {
    suspend fun getColorModelNames(): List<String>
    suspend fun getAColor(model: String): List<List<Int>>
}

class ColorRepositoryImpl(
    private val api: ColorApi
) : ColorRepository {

    override suspend fun getColorModelNames(): List<String> {
        return api.getColorModelList()
    }

    override suspend fun getAColor(model: String): List<List<Int>> {
        return api.getColorModel(model = model)
    }
}

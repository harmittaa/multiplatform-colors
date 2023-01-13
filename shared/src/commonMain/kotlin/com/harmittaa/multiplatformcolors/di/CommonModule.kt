package com.harmittaa.multiplatformcolors.di

import com.harmittaa.multiplatformcolors.repository.ColorRepository
import com.harmittaa.multiplatformcolors.repository.ColorRepositoryImpl
import org.koin.dsl.module

internal val commonModule = module {
    single<ColorRepository> { ColorRepositoryImpl(api = get()) }
}

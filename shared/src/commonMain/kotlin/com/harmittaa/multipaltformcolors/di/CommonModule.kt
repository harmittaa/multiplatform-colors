package com.harmittaa.multipaltformcolors.di

import com.harmittaa.multipaltformcolors.repository.ColorRepository
import com.harmittaa.multipaltformcolors.repository.ColorRepositoryImpl
import org.koin.dsl.module

internal val commonModule = module {
    single<ColorRepository> { ColorRepositoryImpl(api = get()) }
}

package com.harmittaa.multiplatformcolors.di

import com.harmittaa.multiplatformcolors.api.ColorApi
import com.harmittaa.multiplatformcolors.api.ColorApiImpl
import com.harmittaa.multiplatformcolors.api.getNetworkClient
import org.koin.dsl.module

internal val networkModule = module {
    single { getNetworkClient() }
    single<ColorApi> { ColorApiImpl(client = get()) }
}

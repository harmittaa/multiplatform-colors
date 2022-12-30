package com.harmittaa.multipaltformcolors.di

import com.harmittaa.multipaltformcolors.api.ColorApi
import com.harmittaa.multipaltformcolors.api.ColorApiImpl
import com.harmittaa.multipaltformcolors.api.getNetworkClient
import org.koin.dsl.module

internal val networkModule = module {
    single { getNetworkClient() }
    single<ColorApi> { ColorApiImpl(client = get()) }
}

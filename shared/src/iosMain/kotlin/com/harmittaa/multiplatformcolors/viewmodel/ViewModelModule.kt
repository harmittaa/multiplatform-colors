package com.harmittaa.multiplatformcolors.viewmodel

import org.koin.dsl.module

internal actual val viewModelModule = module {
    factory { ColorScreenViewModel(repository = get()) }
}

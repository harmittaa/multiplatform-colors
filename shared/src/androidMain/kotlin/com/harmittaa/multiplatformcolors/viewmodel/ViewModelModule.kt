package com.harmittaa.multiplatformcolors.viewmodel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val viewModelModule: Module = module {
    viewModel { ColorScreenViewModel() }
}

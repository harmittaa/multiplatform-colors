package com.harmittaa.multipaltformcolors.di

import com.harmittaa.multipaltformcolors.Greeting
import org.koin.dsl.module

val commonModule = module {
    single { Greeting(client = get()) }
}

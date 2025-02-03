package com.ith.partygames.screens.main.di

import com.ith.partygames.screens.main.presentation.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainScreenModule = module {

    viewModelOf(::MainViewModel)
}
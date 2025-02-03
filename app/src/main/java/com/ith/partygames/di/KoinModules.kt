package com.ith.partygames.di

import com.ith.partygames.screens.main.di.mainScreenModule
import org.koin.core.module.Module

private val screenModules = listOf(
    mainScreenModule,
)

val koinModules: List<Module> = screenModules

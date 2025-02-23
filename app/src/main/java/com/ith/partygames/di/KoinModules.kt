package com.ith.partygames.di

import com.ith.partygames.common.data.di.commonDataModule
import com.ith.partygames.screens.common_connection.common.di.commonConnectionScreenModule
import com.ith.partygames.screens.main.di.mainScreenModule
import org.koin.core.module.Module

private val screenModules = listOf(
    mainScreenModule,
    commonConnectionScreenModule,
)

private val commonModules = listOf(
    commonDataModule
)

val koinModules: List<Module> = commonModules + screenModules

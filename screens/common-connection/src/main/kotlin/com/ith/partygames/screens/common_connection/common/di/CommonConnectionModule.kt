package com.ith.partygames.screens.common_connection.common.di

import com.ith.partygames.screens.common_connection.client.di.clientModule
import com.ith.partygames.screens.common_connection.host.di.hostModule
import com.ith.partygames.screens.common_connection.main.presentation.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val commonConnectionScreensModule = module {
    viewModelOf(::MainViewModel)

    includes(clientModule, hostModule)
}

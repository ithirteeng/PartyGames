package com.ith.partygames.screens.common_connection.common.di

import com.ith.partygames.screens.common_connection.client.presentation.ClientViewModel
import com.ith.partygames.screens.common_connection.host.presentation.HostViewModel
import com.ith.partygames.screens.common_connection.main.presentation.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val commonConnectionScreensModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::HostViewModel)
    viewModelOf(::ClientViewModel)
}

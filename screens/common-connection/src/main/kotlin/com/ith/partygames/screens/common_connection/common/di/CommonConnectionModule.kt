package com.ith.partygames.screens.common_connection.common.di

import androidx.lifecycle.SavedStateHandle
import com.ith.partygames.screens.common_connection.host.presentation.HostViewModel
import com.ith.partygames.screens.common_connection.main.presentation.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val commonConnectionScreenModule = module {

    viewModel { (handle: SavedStateHandle) -> MainViewModel(handle) }
    viewModel { (handle: SavedStateHandle) -> HostViewModel(handle, get()) }
}

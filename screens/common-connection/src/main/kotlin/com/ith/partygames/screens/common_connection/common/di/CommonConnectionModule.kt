package com.ith.partygames.screens.common_connection.common.di

import com.ith.partygames.screens.common_connection.main.presentation.CommonConnectionMainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val commonConnectionScreenModule = module {

    viewModelOf(::CommonConnectionMainViewModel)
}
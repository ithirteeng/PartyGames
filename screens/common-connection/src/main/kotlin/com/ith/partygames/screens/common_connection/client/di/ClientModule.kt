package com.ith.partygames.screens.common_connection.client.di

import com.ith.partygames.screens.common_connection.client.data.ClientRepositoryImpl
import com.ith.partygames.screens.common_connection.client.domain.ClientRepository
import com.ith.partygames.screens.common_connection.client.presentation.ClientViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

internal val clientModule = module {

    singleOf(::ClientRepositoryImpl) withOptions {
        bind<ClientRepository>()
    }

    viewModelOf(::ClientViewModel)
}

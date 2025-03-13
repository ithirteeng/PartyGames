package com.ith.partygames.screens.common_connection.client.di

import com.ith.partygames.screens.common_connection.client.data.ClientRepositoryImpl
import com.ith.partygames.screens.common_connection.client.domain.ClientRepository
import com.ith.partygames.screens.common_connection.client.presentation.ClientViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val clientModule = module {

    single<ClientRepository> {
        ClientRepositoryImpl(
            server = get(named(com.ith.partygames.common.server.NodeServer.TYPE_CLIENT))
        )
    }

    viewModelOf(::ClientViewModel)
}

package com.ith.partygames.screens.common_connection.host.di

import com.ith.partygames.screens.common_connection.host.data.HostRepositoryImpl
import com.ith.partygames.screens.common_connection.host.domain.HostRepository
import com.ith.partygames.screens.common_connection.host.presentation.HostViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val hostModule = module {

    single<HostRepository> {
        HostRepositoryImpl(
            server = get(named(com.ith.partygames.common.server.NodeServer.TYPE_HOST))
        )
    }

    viewModelOf(::HostViewModel)
}

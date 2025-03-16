package com.ith.partygames.screens.common_connection.host.di

import com.ith.partygames.common.server.GameServerRouter
import com.ith.partygames.screens.common_connection.host.data.HostRepositoryImpl
import com.ith.partygames.screens.common_connection.host.data.router.CommonConnectionHostRouter
import com.ith.partygames.screens.common_connection.host.domain.HostRepository
import com.ith.partygames.screens.common_connection.host.presentation.HostViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module

internal val hostModule = module {

    viewModelOf(::HostViewModel)

    singleOf(::HostRepositoryImpl) withOptions {
        bind<HostRepository>()
    }

    factoryOf(::CommonConnectionHostRouter) withOptions {
        bind<GameServerRouter>()
    }
}

package com.ith.partygames.common.server.di

import com.ith.partygames.common.server.GameServerRouter
import com.ith.partygames.common.server.NodeServer
import org.koin.dsl.module

val commonServerModule = module {

    single {
        NodeServer(
            port = NodeServer.HOST_PORT,
            routers = getAll<GameServerRouter>()
        )
    }
}

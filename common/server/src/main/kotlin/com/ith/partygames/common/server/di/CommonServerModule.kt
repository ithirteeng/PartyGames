package com.ith.partygames.common.server.di

import com.ith.partygames.common.server.GameServerRouter
import com.ith.partygames.common.server.NodeServer
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonServerModule = module {

    single(named(NodeServer.TYPE_HOST)) {
        NodeServer(
            port = NodeServer.HOST_PORT,
            routers = getAllWithQualifier<GameServerRouter>(named(NodeServer.TYPE_HOST))
        )
    }

    single(named(NodeServer.TYPE_CLIENT)) {
        NodeServer(
            port = NodeServer.CLIENT_PORT,
            routers = getAllWithQualifier<GameServerRouter>(named(NodeServer.TYPE_CLIENT))
        )
    }

}

package com.ith.partygames.common.data.di

import com.ith.partygames.common.data.LocalNode
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonDataModule = module {

    singleOf(LocalNode::createLocalNode)
}

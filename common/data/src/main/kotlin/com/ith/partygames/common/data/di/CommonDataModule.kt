package com.ith.partygames.common.data.di

import com.ith.partygames.common.data.provider.LocalNodeProvider
import com.ith.partygames.common.data.provider.OkHttpProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonDataModule = module {

    singleOf(LocalNodeProvider::createLocalNode)
    singleOf(OkHttpProvider::createOkHttpClient)
}

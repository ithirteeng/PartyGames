package com.ith.partygames.common.server.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

inline fun <reified T : Any> Scope.getAllWithQualifier(qualifier: Qualifier): List<T> {
    return this.getAll<T>().filter {
        try {
            this.get<T>(qualifier) == it
        } catch (e: Exception) {
            false
        }
    }
}

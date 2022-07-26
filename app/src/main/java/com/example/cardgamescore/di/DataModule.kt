package com.example.cardgamescore.di

import com.example.cardgamescore.local_data_source.dao.PlayerDaoDataSource
import com.example.cardgamescore.local_data_source.repository.LocalDataRepository
import com.example.cardgamescore.local_data_source.repository.LocalDataRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single <LocalDataRepository> { LocalDataRepositoryImpl(get() as PlayerDaoDataSource) }
}
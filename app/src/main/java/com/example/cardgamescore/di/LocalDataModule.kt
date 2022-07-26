package com.example.cardgamescore.di

import LocalDataBase
import com.example.cardgamescore.local_data_source.dao.PlayerDaoDataSource
import com.example.cardgamescore.local_data_source.repository.LocalDataRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {
    single { LocalDataRepositoryImpl(get()) }
    single { LocalDataBase.getInstance(androidContext()) }
    factory { get<LocalDataBase>().playerDao() }
}
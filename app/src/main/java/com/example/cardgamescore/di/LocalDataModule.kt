package com.example.cardgamescore.di

import LocalDataBase
import com.example.cardgamescore.local_data_source.dao.PlayerDaoDataSource
import com.example.cardgamescore.local_data_source.repository.LocalDataRepository
import com.example.cardgamescore.local_data_source.repository.LocalDataRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {
//    single { LocalDataBase.getInstance(androidContext()) }
//    single { get<LocalDataBase>().playerDao() }

//    single<LocalDataRepository> {
//        LocalDataRepositoryImpl(
//            get() as PlayerDaoDataSource
//        )
//    }
}
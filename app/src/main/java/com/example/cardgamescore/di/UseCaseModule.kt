package com.example.cardgamescore.di

import com.example.cardgamescore.local_data_source.use_case.DeletePlayerUseCase
import com.example.cardgamescore.local_data_source.use_case.GetAllPlayerDataUseCase
import com.example.cardgamescore.local_data_source.use_case.InsertPlayerUseCase
import com.example.cardgamescore.local_data_source.use_case.UpdatePlayerUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetAllPlayerDataUseCase() }
    single { InsertPlayerUseCase() }
    single { DeletePlayerUseCase() }
    single { UpdatePlayerUseCase() }
}
package com.example.cardgamescore.di

import com.example.cardgamescore.score_fragment.ScoreViewModel
import com.example.cardgamescore.splash_fragment.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ScoreViewModel(get(), get(), get(), get()) }
    viewModel { SplashViewModel() }
}
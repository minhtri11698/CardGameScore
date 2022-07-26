package com.example.cardgamescore.base

import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

abstract class BaseViewModel: ViewModel(), KoinComponent {
    open fun <T> handleCallData(state: State<T>): T? {
        return when (state) {
            is State.Success -> {
                state.data
            }
            is State.Error -> {
                null
            }
        }
    }
}
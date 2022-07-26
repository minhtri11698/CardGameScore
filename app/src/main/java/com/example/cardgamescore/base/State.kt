package com.example.cardgamescore.base

sealed class State<T> {
    data class Success<T>(val data: T) : State<T>()

    data class Error<T>(val exception: Exception) : State<T>()

    companion object {
        fun <T> onSuccess(data: T) = Success(data)

        fun <T> onError(exception: Exception) = Error<T>(exception)
    }
}
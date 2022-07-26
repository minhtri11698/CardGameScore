package com.example.cardgamescore.base

import androidx.annotation.MainThread
import com.example.cardgamescore.local_data_source.repository.LocalDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseUseCase<INPUT, OUTPUT>: KoinComponent {
    val localDataRepository: LocalDataRepository by inject()

    @MainThread
    protected abstract suspend fun buildUseCase(input: INPUT): OUTPUT

    suspend fun execute(
        input: INPUT,
        doCompleted: (OUTPUT) -> Unit = {},
        doError: (Exception) -> Unit = {}
    ) = flow {
        try {
            val response = buildUseCase(input)
            emit(State.onSuccess(response))
            doCompleted.invoke(response)
        } catch (e: Exception) {
            emit(State.onError(e))
            doError.invoke(e)
        }
    }.flowOn(Dispatchers.IO)
}
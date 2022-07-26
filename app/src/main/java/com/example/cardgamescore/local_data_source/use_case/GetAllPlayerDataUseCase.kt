package com.example.cardgamescore.local_data_source.use_case

import com.example.cardgamescore.base.BaseUseCase
import com.example.cardgamescore.model.Player

class GetAllPlayerDataUseCase: BaseUseCase<Unit, ArrayList<Player>>() {
    override suspend fun buildUseCase(input: Unit): ArrayList<Player> =
        localDataRepository.getAllPlayer()
}

class DeletePlayerUseCase: BaseUseCase<Int, Unit>() {
    override suspend fun buildUseCase(input: Int) = localDataRepository.deletePlayer(input)
}

class InsertPlayerUseCase: BaseUseCase<Player, Unit>() {
    override suspend fun buildUseCase(input: Player) {
        return localDataRepository.insertPlayer(input)
    }
}

class UpdatePlayerUseCase: BaseUseCase<Player, Unit>() {
    override suspend fun buildUseCase(input: Player) {
        return localDataRepository.updatePlayer(input)
    }

}
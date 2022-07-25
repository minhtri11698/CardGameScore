package com.example.cardgamescore.local_data_source.repository

import com.example.cardgamescore.local_data_source.dao.PlayerDaoDataSource
import com.example.cardgamescore.model.Player

class LocalDataRepositoryImpl(
    private val playerDaoDataSource: PlayerDaoDataSource
): LocalDataRepository {
    override suspend fun getAllPlayer(): ArrayList<Player> = playerDaoDataSource.getAllPlayer()

    override suspend fun deletePlayer(playerId: Int) = playerDaoDataSource.deletePlayer(playerId)

    override suspend fun updatePlayer(player: Player) = playerDaoDataSource.updatePlayer(player)

    override suspend fun insertPlayer(player: Player) = playerDaoDataSource.updatePlayer(player)
}
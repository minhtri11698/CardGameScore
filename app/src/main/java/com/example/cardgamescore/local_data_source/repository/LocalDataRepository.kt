package com.example.cardgamescore.local_data_source.repository

import com.example.cardgamescore.model.Player

interface LocalDataRepository {
    suspend fun getAllPlayer(): ArrayList<Player>

    suspend fun deletePlayer(playerId: Int)

    suspend fun updatePlayer(player: Player)

    suspend fun insertPlayer(player: Player)
}
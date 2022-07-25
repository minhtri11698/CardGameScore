package com.example.cardgamescore.local_data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cardgamescore.model.Player

@Dao
interface PlayerDaoDataSource {
    @Query("SELECT * FROM players")
    suspend fun getAllPlayer(): ArrayList<Player>

    @Insert
    suspend fun insertPlayer(newPlayer: Player)

    @Query("DELETE FROM players WHERE playerId = :playerId")
    suspend fun deletePlayer(playerId: Int)

    @Update
    suspend fun updatePlayer(player: Player)
}
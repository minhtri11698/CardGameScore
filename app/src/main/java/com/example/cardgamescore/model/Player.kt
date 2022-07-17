package com.example.cardgamescore.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class Player(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "playerId")
    val playerId: Int = 0,
    @ColumnInfo(name = "playerName")
    val playerName: String = "Player $playerId",
    @ColumnInfo(name = "score")
    var playerPoint: Int = 0,
    @ColumnInfo(name = "isHost")
    var isHost: Boolean = false,
) {
}
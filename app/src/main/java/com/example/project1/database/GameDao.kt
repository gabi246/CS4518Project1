package com.example.project1.database

import androidx.room.Dao
import androidx.room.Query
import com.example.project1.Game
import java.util.*

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getGames(): List<Game>
    @Query("SELECT * FROM game WHERE id=(:id)")
    fun getGames(id: UUID): Game?
}


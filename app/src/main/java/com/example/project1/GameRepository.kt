package com.example.project1

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.project1.com.example.project1.Game
import com.example.project1.database.GameDatabase
import java.io.File
import java.util.*

private const val DATABASE_NAME = "game-database"

class GameRepository private constructor(context: Context) {
    private val database : GameDatabase = Room.databaseBuilder(
        context.applicationContext,
        GameDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val gameDao = database.gameDao()

    private val filesDir = context.applicationContext.filesDir

    fun getGames(): LiveData<List<Game>> = gameDao.getGames()

    fun getGame(id: UUID): LiveData<Game?> = gameDao.getGame(id)

    fun getTeamAPhotoFile(game: Game): File = File(filesDir, game.teamAPhotoFileName)
    fun getTeamBPhotoFile(game: Game): File = File(filesDir, game.teamBPhotoFileName)

    companion object {
        private var INSTANCE: GameRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = GameRepository(context)
            }
        }
        fun get(): GameRepository {
            return INSTANCE ?:
            throw IllegalStateException("GameRepository must be initialized")
        }
    }
}
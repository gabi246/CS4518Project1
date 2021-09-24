package com.example.project1

import android.content.Context

class GameRepository private constructor(context: Context) {
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
package com.example.coincapshortc.model.db

import com.example.coincapshortc.model.model.Game

class GameDatabase {

    suspend fun hasGames() = true

    suspend fun writeGames(games: List<Game>) {}

    suspend fun readGames(): List<Game> = emptyList()
}
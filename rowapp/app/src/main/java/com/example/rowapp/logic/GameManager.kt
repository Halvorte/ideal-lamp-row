package com.example.rowapp.logic

import com.example.rowapp.dataClasses.Game
import com.example.rowapp.dataClasses.GameState
import com.example.rowapp.service.GameService

object GameManager {
    var player:String? = null
    var state:GameState? = null

    val StartingGameState: GameState = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,0))

    fun createGame(player:String){
        GameService.createGame(player, StartingGameState) { game: Game?, err: Int? ->
            if (err != null){
                // What is the error code
            } else{
                // we have a game. what to do..
            }
        }
    }

    fun joinGame(player: String, gameId:String){
        GameService.joinGame(player, gameId) {  game: Game?, err: Int? ->
            if (err != null){
                print(err)

            } else {
                print(err)
                //var tmp = game?.players
            }
        }

    }

    fun updateGame(gameId: String, gameState: GameState){
        GameService.updateGame(gameId, gameState){  game: Game?, err: Int? ->
            if (err != null){
                print(err)

            } else {
                print(err)
                //var tmp = game?.players
            }
        }
    }

    fun pollGame(gameId: String){
        GameService.pollGame(gameId){  game: Game?, err: Int? ->
            if (err != null){
                print(err)

            } else {
                print(err)
                //var tmp = game?.players
            }
        }
    }

}
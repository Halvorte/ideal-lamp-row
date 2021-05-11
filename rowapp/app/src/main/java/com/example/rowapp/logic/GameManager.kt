package com.example.rowapp.logic

import android.content.Intent
import com.example.rowapp.App.Companion.context
import com.example.rowapp.dataClasses.Game
import com.example.rowapp.dataClasses.GameState
import com.example.rowapp.service.GameService
import com.example.rowapp.ui.GameActivity
import com.example.rowapp.ui.MainActivity
import com.google.android.material.internal.ContextUtils.getActivity


object GameManager {
    var players:MutableList<String>? = null
    var state:GameState? = null
    var gameId:String? = null

    val StartingGameState: GameState = listOf(listOf(0, 0, 0), listOf(0, 0, 0), listOf(0, 0, 0))

    fun createGame(player: String){
        GameService.createGame(player, StartingGameState) { game: Game?, err: Int? ->
            if (err != null){
                // What is the error code
            } else{
                // we have a game. what to do.. Start a new activity with the game id in the top.
                players = game?.players
                gameId = game?.gameId

                // Start Game Activity
                val intent = Intent(context, GameActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }


    fun joinGame(player: String, gameId: String){
        GameService.joinGame(player, gameId) { game: Game?, err: Int? ->
            if (err != null){
                //print(err)

            } else {
                //print(err)
                // Start new activity with the game
                val intent = Intent(context, GameActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)

            }
        }

    }

    fun updateGame(gameId: String, gameState: GameState){
        GameService.updateGame(gameId, gameState){ game: Game?, err: Int? ->
            if (err != null){
                print(err)

            } else {
                print(err)
                //var tmp = game?.players
            }
        }
    }

    fun pollGame(gameId: String){
        GameService.pollGame(gameId){ game: Game?, err: Int? ->
            if (err != null){
                print(err)

            } else {
                print(err)
                //var tmp = game?.players
            }
        }
    }

}
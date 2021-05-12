package com.example.rowapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.rowapp.R
import com.example.rowapp.dataClasses.GameState
import com.example.rowapp.databinding.ActivityGameBinding
import com.example.rowapp.logic.EXTRA_MESSAGE
import com.example.rowapp.logic.GameManager
import kotlinx.android.parcel.RawValue
import kotlinx.android.synthetic.main.activity_game.*
import android.os.Handler
import android.os.Looper

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private var player1Turn: Boolean = true

    lateinit private var currentGameState: GameState

    private lateinit var rstGameState: GameState
    private lateinit var mutableLst: GameState

    lateinit var gameId: String

    private var players:MutableList<String>? = null

    private var roundCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        gameId = message.toString()

        currentGameState = mutableListOf(mutableListOf(0,0,0), mutableListOf(0,0,0), mutableListOf(0,0,0))
        //var mutableLst = currentGameState.toMutableList()

        rstGameState = mutableListOf(mutableListOf(0,0,0), mutableListOf(0,0,0), mutableListOf(0,0,0))

        supportActionBar?.title = "Game id: $gameId"

        // Set in game names. For future set in game name every 5 seconds if two names
        GameManager.pollGame(gameId)
        var en = GameManager.players?.get(0)
        //var to = GameManager.players?.get(1)
        binding.textViewP1.setText("$en :")
        //binding.textViewP2.setText("$to :")

        // Loop every 5 seconds
        /*
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            GameManager.pollGame(gameId)
            currentGameState = GameManager.state!!
            players = GameManager.players
        }, 5000)
         */

        //Set the roundCount to 0
        roundCount = 0


        // Set names in game
        /*
        if (players?.size == 2){
            binding.textViewP1 = players[0]
            binding.textViewP2 = players[1]
        }else if (players?.size ==1){
            binding.textViewP1 = players[0]
        }else{
            println("Only one player so far")
        }

         */


        // Back button in actionbar
        val actionbar = supportActionBar
        //actionbar.title("gameId")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // reset button
        binding.resetButton.setOnClickListener {
            resetBtn()
        }

        //
        binding.button00.setOnClickListener {
            if (button_00.text.toString().equals("")) {
                btn00Action()
            }
        }
    }

    private fun resetBtn(){
        GameManager.pollGame(gameId)
        players = GameManager.players
        var tmp = players?.size
        if (GameManager.players?.size == 2){
            GameManager.updateGame(gameId, rstGameState)
        }else if (GameManager.players?.size == 1){
            println("Not two players")
        }
    }


    private fun btn00Action() {
        if (player1Turn) {
            // Sets the button to an X
            binding.button00.text = "X"
            currentGameState[0][0] = "X"
            //println(currentGameState)
        } else {
            // Sets the button to an O
            binding.button00.text = "O"
            currentGameState[0][0] = "O"
        }
        // Update the game after a button is pressed.
        GameManager.updateGame(gameId, currentGameState)
        // Update roundCount / turn counter
        roundCount++
    }

}


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
import android.widget.Toast
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.timer
import kotlin.properties.Delegates

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private var player1Turn: Boolean = true

    lateinit private var currentGameState: GameState

    private lateinit var rstGameState: GameState
    private lateinit var mutableLst: GameState

    lateinit var gameId: String

    private var players:MutableList<String>? = null

    private var roundCount = 0

    private var twoPlayers:Boolean = false

    private var symbol: Boolean = true

    private var timerStop by Delegates.notNull<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // GameId from where the activity is started.
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        gameId = message.toString()


        currentGameState = mutableListOf(mutableListOf(0,0,0), mutableListOf(0,0,0), mutableListOf(0,0,0))
        rstGameState = mutableListOf(mutableListOf(0,0,0), mutableListOf(0,0,0), mutableListOf(0,0,0))

        // Set the title to the gameId
        supportActionBar?.title = "Game id: $gameId"

        timerStop = false

        // Set player one name
        players = GameManager.players
        binding.textViewP1.text = "P1:"+players?.get(0)

        //Set the roundCount to 0
        roundCount = 0

        // Back button in actionbar
        val actionbar = supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // reset button
        binding.resetButton.setOnClickListener {
            resetBtn()
        }

        //symbol button
        binding.symbolBtn.setOnClickListener { symBtn() }

        // Buutons onClick listeners
        binding.button00.setOnClickListener { btn00Action() }
        binding.button01.setOnClickListener { btn01Action() }
        binding.button02.setOnClickListener { btn02Action() }

        binding.button10.setOnClickListener { btn10Action() }
        binding.button11.setOnClickListener { btn11Action() }
        binding.button12.setOnClickListener { btn12Action() }

        binding.button20.setOnClickListener { btn20Action() }
        binding.button21.setOnClickListener { btn21Action() }
        binding.button22.setOnClickListener { btn22Action() }

        // Loop every 5 seconds
        fixedRateTimer("Poll timer", false, 0, 5000){
            runOnUiThread {
                GameManager.pollGame(gameId)
                twoPlayers = GameManager.twoPlayers
                if (GameManager.players?.size == 2){ //twoPlayers
                    players = GameManager.players

                    if (players?.size == 2){
                        var en = players?.get(0)
                        binding.textViewP1.text = "P1:$en"
                        var to = players?.get(1)
                        binding.textViewP2.text = "P2:$to"
                    }

                    if (currentGameState != rstGameState){
                        currentGameState = GameManager.state!!
                    }
                    updateLayout()
                    checkXWin()
                    checkOwin()
                }else{
                    Toast.makeText(this@GameActivity, "Waiting for second player", Toast.LENGTH_SHORT).show()
                }
            }
            if (timerStop == true){
                this.cancel()
            }
        }

    }

    private fun checkOwin() {
        if (currentGameState[0][0] == "O" && currentGameState[0][1] == "O" && currentGameState[0][2] == "O"){
            Toast.makeText(this@GameActivity, players?.get(1)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[1][0] == "O" && currentGameState[1][1] == "O" && currentGameState[1][2] == "O"){
            Toast.makeText(this@GameActivity, players?.get(1)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[2][0] == "O" && currentGameState[2][1] == "O" && currentGameState[2][2] == "O"){
            Toast.makeText(this@GameActivity, players?.get(1)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[0][0] == "O" && currentGameState[1][0] == "O" && currentGameState[2][0] == "O"){
            Toast.makeText(this@GameActivity, players?.get(1)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[0][1] == "O" && currentGameState[1][1] == "O" && currentGameState[2][1] == "O"){
            Toast.makeText(this@GameActivity, players?.get(1)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[0][2] == "O" && currentGameState[1][2] == "O" && currentGameState[2][2] == "O"){
            Toast.makeText(this@GameActivity, players?.get(1)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[0][0] == "O" && currentGameState[1][1] == "O" && currentGameState[2][2] == "O"){
            Toast.makeText(this@GameActivity, players?.get(1)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[0][2] == "O" && currentGameState[1][1] == "O" && currentGameState[2][0] == "O"){
            Toast.makeText(this@GameActivity, players?.get(1)+"Wins", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkXWin() {
        if (currentGameState[0][0] == "X" && currentGameState[0][1] == "X" && currentGameState[0][2] == "X"){
            Toast.makeText(this@GameActivity, players?.get(0)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[1][0] == "X" && currentGameState[1][1] == "X" && currentGameState[1][2] == "X"){
            Toast.makeText(this@GameActivity, players?.get(0)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[2][0] == "X" && currentGameState[2][1] == "X" && currentGameState[2][2] == "X"){
            Toast.makeText(this@GameActivity, players?.get(0)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[0][0] == "X" && currentGameState[1][0] == "X" && currentGameState[2][0] == "X"){
            Toast.makeText(this@GameActivity, players?.get(0)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[0][1] == "X" && currentGameState[1][1] == "X" && currentGameState[2][1] == "X"){
            Toast.makeText(this@GameActivity, players?.get(0)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[0][2] == "X" && currentGameState[1][2] == "X" && currentGameState[2][2] == "X"){
            Toast.makeText(this@GameActivity, players?.get(0)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[0][0] == "X" && currentGameState[1][1] == "X" && currentGameState[2][2] == "X"){
            Toast.makeText(this@GameActivity, players?.get(0)+"Wins", Toast.LENGTH_SHORT).show()
        }else if (currentGameState[0][2] == "X" && currentGameState[1][1] == "X" && currentGameState[2][0] == "X"){
            Toast.makeText(this@GameActivity, players?.get(0)+"Wins", Toast.LENGTH_SHORT).show()
        }
    }

    // function to stop the activity with the back button
    override fun onSupportNavigateUp(): Boolean {
        timerStop = true
        GameManager.twoPlayers = false
        twoPlayers = false
        onBackPressed()
        finish()
        return true
    }


    // Update the buttons text according to polled game state
    private fun updateLayout(){
        if (currentGameState[0][0].toString() == "X" || currentGameState[0][0].toString() == "O"){
            binding.button00.text = currentGameState[0][0].toString()
        }else{ binding.button00.text = "" }
        if (currentGameState[0][1].toString() == "X" || currentGameState[0][1].toString() == "O"){
            binding.button01.text = currentGameState[0][1].toString()
        }else{binding.button01.text = ""}
        if (currentGameState[0][2].toString() == "X" || currentGameState[0][2].toString() == "O"){
            binding.button02.text = currentGameState[0][2].toString()
        }else{binding.button02.text = ""}

        if (currentGameState[1][0].toString() == "X" || currentGameState[1][0].toString() == "O"){
            binding.button10.text = currentGameState[1][0].toString()
        }else{binding.button10.text = ""}
        if (currentGameState[1][1].toString() == "X" || currentGameState[1][1].toString() == "O"){
            binding.button11.text = currentGameState[1][1].toString()
        }else{binding.button11.text = ""}
        if (currentGameState[1][2].toString() == "X" || currentGameState[1][2].toString() == "O"){
            binding.button12.text = currentGameState[1][2].toString()
        }else{binding.button12.text = ""}

        if (currentGameState[2][0].toString() == "X" || currentGameState[2][0].toString() == "O"){
            binding.button20.text = currentGameState[2][0].toString()
        }else{binding.button20.text = ""}
        if (currentGameState[2][1].toString() == "X" || currentGameState[2][1].toString() == "O"){
            binding.button21.text = currentGameState[2][1].toString()
        }else{binding.button21.text = ""}
        if (currentGameState[2][2].toString() == "X" || currentGameState[2][2].toString() == "O"){
            binding.button22.text = currentGameState[2][2].toString()
        }else{binding.button22.text = ""}

        //binding.button01.text = currentGameState[0][1].toString()
    }

    private fun resetBtn(){
        if (twoPlayers){
            GameManager.updateGame(gameId, rstGameState)
        }else if (players?.size == 1){
            println("Not two players")
            Toast.makeText(this@GameActivity, "Still waiting for second player", Toast.LENGTH_SHORT).show()
        }
    }

    // function for choosing symbol.
    private fun symBtn(){
        symbol = symbol.not()
        if (symbol){
            binding.symbolBtn.text = "X"
        }else{binding.symbolBtn.text = "O"}
    }


    private fun btn00Action() {
        if (twoPlayers){
            if (symbol){
                binding.button00.text = "X"
                currentGameState[0][0] = "X"
            }else{
                binding.button00.text = "O"
                currentGameState[0][0] = "O"
            }
            GameManager.updateGame(gameId, currentGameState)
        }
    }
    private fun btn01Action() {
        if (twoPlayers){
            if (symbol){
                binding.button01.text = "X"
                currentGameState[0][1] = "X"
            }else{
                binding.button01.text = "O"
                currentGameState[0][1] = "O"
            }
            GameManager.updateGame(gameId, currentGameState)
        }
    }
    private fun btn02Action() {
        if (twoPlayers){
            if (symbol){
                binding.button02.text = "X"
                currentGameState[0][2] = "X"
            }else{
                binding.button02.text = "O"
                currentGameState[0][2] = "O"
            }
            GameManager.updateGame(gameId, currentGameState)
        }
    }

    private fun btn10Action() {
        if (twoPlayers){
            if (symbol){
                binding.button10.text = "X"
                currentGameState[1][0] = "X"
            }else{
                binding.button10.text = "O"
                currentGameState[1][0] = "O"
            }
            GameManager.updateGame(gameId, currentGameState)
        }
    }
    private fun btn11Action() {
        if (twoPlayers){
            if (symbol){
                binding.button11.text = "X"
                currentGameState[1][1] = "X"
            }else{
                binding.button11.text = "O"
                currentGameState[1][1] = "O"
            }
            GameManager.updateGame(gameId, currentGameState)
        }
    }
    private fun btn12Action() {
        if (twoPlayers){
            if (symbol){
                binding.button12.text = "X"
                currentGameState[1][2] = "X"
            }else{
                binding.button12.text = "O"
                currentGameState[1][2] = "O"
            }
            GameManager.updateGame(gameId, currentGameState)
        }
    }

    private fun btn20Action() {
        if (twoPlayers){
            if (symbol){
                binding.button20.text = "X"
                currentGameState[2][0] = "X"
            }else{
                binding.button20.text = "O"
                currentGameState[2][0] = "O"
            }
            GameManager.updateGame(gameId, currentGameState)
        }
    }
    private fun btn21Action() {
        if (twoPlayers){
            if (symbol){
                binding.button21.text = "X"
                currentGameState[2][1] = "X"
            }else{
                binding.button21.text = "O"
                currentGameState[2][1] = "O"
            }
            GameManager.updateGame(gameId, currentGameState)
        }
    }
    private fun btn22Action() {
        if (twoPlayers){
            if (symbol){
                binding.button22.text = "X"
                currentGameState[2][2] = "X"
            }else{
                binding.button22.text = "O"
                currentGameState[2][2] = "O"
            }
            GameManager.updateGame(gameId, currentGameState)
        }
    }

}


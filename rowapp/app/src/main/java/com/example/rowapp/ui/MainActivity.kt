package com.example.rowapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.rowapp.App
import com.example.rowapp.R
import com.example.rowapp.databinding.ActivityMainBinding
import com.example.rowapp.logic.GameManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var localGameButton: Button
    private lateinit var newGameButton: Button
    private lateinit var joinGameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setImageResource(R.mipmap.logo)

        //GameManager.createGame("Kenneth")

        //GameManager.joinGame("John", "3t3lc")

        //GameManager.updateGame("3t3lc", mutableListOf(mutableListOf(0,0,0), mutableListOf(0,"X",0), mutableListOf(0,0,0)))

        //GameManager.pollGame("o0fxt")

        // Function for new game dialog box
        //localGameDialogbox()
        newGameDialogbox()
        joinGameDialogbox()
    }

    /*
    private fun localGameDialogbox() {
        localGameButton = findViewById<Button>(R.id.localGameBtn)
        localGameButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.local_game_dialogbox, null)
            val editPlayer1 = dialogLayout.findViewById<EditText>(R.id.dialogbox_local_p1)
            val editPlayer2 = dialogLayout.findViewById<EditText>(R.id.dialogbox_local_p2)

            with(builder) {
                setTitle("Enter name of players")
                setPositiveButton("OK") { dialog, which ->
                    // What happens when player enters name and creates game
                    val player1 = editPlayer1.text.toString()
                    val player2 = editPlayer2.text.toString()

                    //GameManager.createGame(player, gameId)

                }
                setNegativeButton("Cancel") { dialog, which ->
                    Log.d("Main", "Negative button clicked")
                }
                setView(dialogLayout)
                show()
            }
        }
    }

     */

    private fun newGameDialogbox() {
        newGameButton = findViewById<Button>(R.id.newGameButton)
        newGameButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.new_game_dialogbox, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.dialogbox_newGame)

            with(builder) {
                setTitle("Enter player name")
                setPositiveButton("OK") { dialog, which ->
                    // What happens when player enters name and creates game
                    val player = editText.text.toString()
                    GameManager.createGame(player)

                }
                setNegativeButton("Cancel") { dialog, which ->
                    Log.d("Main", "Negative button clicked")
                }
                setView(dialogLayout)
                show()
            }
        }
    }


    fun joinGameDialogbox() {
        joinGameButton = findViewById<Button>(R.id.joinGameButton)
        joinGameButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.join_game_dialogbox, null)
            val editPlayer = dialogLayout.findViewById<EditText>(R.id.dialogbox_join_player)
            val editId = dialogLayout.findViewById<EditText>(R.id.dialogbox_join_id)

            with(builder) {
                setTitle("Enter player name and Id")
                setPositiveButton("OK") { dialog, which ->
                    // What happens when player enters name and creates game
                    val player = editPlayer.text.toString()
                    val game_Id = editId.text.toString()
                    GameManager.joinGame(player, game_Id)

                }
                setNegativeButton("Cancel") { dialog, which ->
                    Log.d("Main", "Negative button clicked")
                }
                setView(dialogLayout)
                show()
            }
        }
    }

}
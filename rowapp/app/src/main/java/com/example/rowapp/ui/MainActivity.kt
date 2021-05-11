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

const val EXTRA_GAME_INFO = "com.example.rowapp.INFO"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var newGameButton: Button
    private lateinit var joinGameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        //GameManager.createGame("Kenneth")

        //GameManager.joinGame("John", "3t3lc")

        //GameManager.updateGame("3t3lc", listOf(listOf(0,0,0), listOf(0,"X",0), listOf(0,0,0)))

        //GameManager.pollGame("3t3lc")

        // Function for new game dialog box
        newGameDialogbox()
        joinGameDialogbox()
    }

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
                    val gameId = editId.text.toString()
                    GameManager.joinGame(player, gameId)

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
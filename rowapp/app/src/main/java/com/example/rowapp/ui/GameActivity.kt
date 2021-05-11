package com.example.rowapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rowapp.R
import com.example.rowapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_game)

        // Back button in actionbar
        val actionbar = supportActionBar
        //actionbar.title("gameId")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
package com.example.rowapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.rowapp.App
import com.example.rowapp.R
import com.example.rowapp.databinding.ActivityMainBinding
import com.example.rowapp.logic.GameManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        //GameManager.createGame("Kenneth")

        //GameManager.joinGame("John", "3t3lc")

        //GameManager.updateGame("3t3lc", listOf(listOf(0,0,0), listOf(0,"X",0), listOf(0,0,0)))

        GameManager.pollGame("3t3lc")
    }
}
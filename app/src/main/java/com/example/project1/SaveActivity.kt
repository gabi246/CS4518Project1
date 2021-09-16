package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

private const val TAG = "SaveActivity"

class SaveActivity : AppCompatActivity() {
    private lateinit var new_game: Button
    private lateinit var game_history: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        new_game = findViewById(R.id.new_game)
        game_history = findViewById(R.id.game_history)

        new_game.setOnClickListener { view: View ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Log.i(TAG, "onClickListener for new_game")
        }
    }
}
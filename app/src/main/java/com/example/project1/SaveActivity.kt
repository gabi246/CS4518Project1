package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders

private const val TAG = "SaveActivity"
//private const val KEY_INDEX = "index"

class SaveActivity : AppCompatActivity() {
    private lateinit var new_game: Button
    private lateinit var game_history: Button
    private lateinit var team_a_input: EditText
    private lateinit var team_b_input: EditText

//    private val bbViewModel: BBViewModel by lazy {
//        ViewModelProviders.of(this).get(BBViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

//        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
//        bbViewModel.currentIndex = currentIndex

        new_game = findViewById(R.id.new_game)
        game_history = findViewById(R.id.game_history)
        team_a_input = findViewById(R.id.team_a_input)
        team_b_input = findViewById(R.id.team_b_input)

        new_game.setOnClickListener { view: View ->
            val team_a_name = team_a_input.text.toString()
            val team_b_name = team_b_input.text.toString()
//            val intent = Intent(this, MainActivity::class.java)
            val intent = MainActivity.newIntent(this@SaveActivity, team_a_name, team_b_name)
            startActivity(intent)
            Log.i(TAG, "onClickListener for new_game")
        }
    }
}
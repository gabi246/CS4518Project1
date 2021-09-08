package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var team_a: TextView
    private lateinit var score_a: TextView
    private lateinit var add_3_a: Button
    private lateinit var add_2_a: Button
    private lateinit var free_throw_a: Button
    private lateinit var team_b: TextView
    private lateinit var score_b: TextView
    private lateinit var add_3_b: Button
    private lateinit var add_2_b: Button
    private lateinit var free_throw_b: Button
    private lateinit var reset: Button

    private val teamA = Team("Team A", 0, false)
    private val teamB = Team("Team B", 0, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        team_a = findViewById(R.id.team_a)
        score_a = findViewById(R.id.score_a)
        add_3_a = findViewById(R.id.add_3_a)
        add_2_a = findViewById(R.id.add_2_a)
        free_throw_a = findViewById(R.id.free_throw_a)
        team_b = findViewById(R.id.team_b)
        score_b = findViewById(R.id.score_b)
        add_3_b = findViewById(R.id.add_3_b)
        add_2_b = findViewById(R.id.add_2_b)
        free_throw_b = findViewById(R.id.free_throw_b)
        reset = findViewById(R.id.reset)

        add_3_a.setOnClickListener { view: View ->
            teamA.score = teamA.score + 3
            score_a.setText(teamA.score)
        }

        add_2_a.setOnClickListener { view: View ->
            teamA.score = teamA.score + 2
            score_a.setText(teamA.score)
        }

        free_throw_a.setOnClickListener { view: View ->
            teamA.score = teamA.score + 1
            score_a.setText(teamA.score)
        }
    }
}
package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View
import android.graphics.Color

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
    private lateinit var game_over: Button

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
        game_over = findViewById(R.id.game_over)

        add_3_a.setOnClickListener { view: View ->
            teamA.score = teamA.score + 3
            score_a.setText(teamA.score.toString())
        }

        add_2_a.setOnClickListener { view: View ->
            teamA.score = teamA.score + 2
            score_a.setText(teamA.score.toString())
        }

        free_throw_a.setOnClickListener { view: View ->
            teamA.score = teamA.score + 1
            score_a.setText(teamA.score.toString())
        }


        add_3_b.setOnClickListener { view: View ->
            teamB.score = teamB.score + 3
            score_b.setText(teamB.score.toString())
        }

        add_2_b.setOnClickListener { view: View ->
            teamB.score = teamB.score + 2
            score_b.setText(teamB.score.toString())
        }

        free_throw_b.setOnClickListener { view: View ->
            teamB.score = teamB.score + 1
            score_b.setText(teamB.score.toString())
        }

        reset.setOnClickListener { view: View ->
            teamA.score = 0
            teamB.score = 0
            score_a.setText(teamA.score.toString())
            score_b.setText(teamB.score.toString())
            score_a.setTextColor(Color.parseColor("#000000"))
            score_b.setTextColor(Color.parseColor("#000000"))
            add_3_a.setClickable(true)
            add_2_a.setClickable(true)
            free_throw_a.setClickable(true)
            add_3_b.setClickable(true)
            add_2_b.setClickable(true)
            free_throw_b.setClickable(true)
        }

        game_over.setOnClickListener { view: View ->
            add_3_a.setClickable(false)
            add_2_a.setClickable(false)
            free_throw_a.setClickable(false)
            add_3_b.setClickable(false)
            add_2_b.setClickable(false)
            free_throw_b.setClickable(false)
            if(checkWinner(teamA, teamB) && checkWinner(teamB, teamA)){
                score_a.setTextColor(Color.parseColor("#32cd32"))
                score_b.setTextColor(Color.parseColor("#32cd32"))
            } else if(checkWinner(teamA, teamB)){
                score_a.setTextColor(Color.parseColor("#32cd32"))
                score_b.setTextColor(Color.parseColor("#ff4500"))
            } else {
                score_a.setTextColor(Color.parseColor("#ff4500"))
                score_b.setTextColor(Color.parseColor("#32cd32"))
            }
        }

        score_a.setText(teamA.score.toString())
        score_b.setText(teamB.score.toString())
    }

    private fun checkWinner(team1: Team, team2: Team): Boolean {
        return team1.score >= team2.score
    }
}
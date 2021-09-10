package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View
import android.graphics.Color
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

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
    private lateinit var winner_a: TextView
    private lateinit var winner_b: TextView

    private val bbViewModel: BBViewModel by lazy {
        ViewModelProviders.of(this).get(BBViewModel::class.java)
    }

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
        winner_a = findViewById(R.id.winner_a)
        winner_b = findViewById(R.id.winner_b)

        add_3_a.setOnClickListener { view: View ->
            score_a.setText(bbViewModel.addPoints("A", 3))
        }

        add_2_a.setOnClickListener { view: View ->
            score_a.setText(bbViewModel.addPoints("A", 2))
        }

        free_throw_a.setOnClickListener { view: View ->
            score_a.setText(bbViewModel.addPoints("A", 1))
        }


        add_3_b.setOnClickListener { view: View ->
            score_b.setText(bbViewModel.addPoints("B", 3))
        }

        add_2_b.setOnClickListener { view: View ->
            score_b.setText(bbViewModel.addPoints("B", 2))
        }

        free_throw_b.setOnClickListener { view: View ->
            score_b.setText(bbViewModel.addPoints("B", 1))
        }

        reset.setOnClickListener { view: View ->
            bbViewModel.setScore("A", 0)
            bbViewModel.setScore("B", 0)
            score_a.setText(bbViewModel.getScore("A"))
            score_b.setText(bbViewModel.getScore("B"))
            score_a.setTextColor(Color.parseColor("#000000"))
            score_b.setTextColor(Color.parseColor("#000000"))
            add_3_a.setClickable(true)
            add_2_a.setClickable(true)
            free_throw_a.setClickable(true)
            add_3_b.setClickable(true)
            add_2_b.setClickable(true)
            free_throw_b.setClickable(true)
            winner_a.setVisibility(View.INVISIBLE)
            winner_b.setVisibility(View.INVISIBLE)
        }

        game_over.setOnClickListener { view: View ->
            add_3_a.setClickable(false)
            add_2_a.setClickable(false)
            free_throw_a.setClickable(false)
            add_3_b.setClickable(false)
            add_2_b.setClickable(false)
            free_throw_b.setClickable(false)
            if(bbViewModel.checkWinner("A", "B") && bbViewModel.checkWinner("B", "A")){
                score_a.setTextColor(Color.parseColor("#32cd32"))
                winner_a.setVisibility(View.VISIBLE)
                score_b.setTextColor(Color.parseColor("#32cd32"))
                winner_b.setVisibility(View.VISIBLE)
            } else if(bbViewModel.checkWinner("A", "B")){
                score_a.setTextColor(Color.parseColor("#32cd32"))
                winner_a.setVisibility(View.VISIBLE)
                score_b.setTextColor(Color.parseColor("#ff4500"))
            } else {
                score_a.setTextColor(Color.parseColor("#ff4500"))
                score_b.setTextColor(Color.parseColor("#32cd32"))
                winner_b.setVisibility(View.VISIBLE)
            }
        }

        score_a.setText(bbViewModel.getScore("A"))
        score_b.setText(bbViewModel.getScore("B"))
    }

}
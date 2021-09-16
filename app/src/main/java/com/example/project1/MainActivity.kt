package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View
import android.graphics.Color
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

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
    private lateinit var save_button: Button
    private lateinit var winner_a: TextView
    private lateinit var winner_b: TextView

    private val bbViewModel: BBViewModel by lazy {
        ViewModelProviders.of(this).get(BBViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        bbViewModel.currentIndex = currentIndex

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
        save_button = findViewById(R.id.save_button)
        winner_a = findViewById(R.id.winner_a)
        winner_b = findViewById(R.id.winner_b)

        add_3_a.setOnClickListener { view: View ->
            score_a.text = bbViewModel.addPoints("A", 3)
            Log.i(TAG, "onClickListener for add_3_a")
        }

        add_2_a.setOnClickListener { view: View ->
            score_a.text = bbViewModel.addPoints("A", 2)
            Log.i(TAG, "onClickListener for add_2_a")
        }

        free_throw_a.setOnClickListener { view: View ->
            score_a.text = bbViewModel.addPoints("A", 1)
            Log.i(TAG, "onClickListener for free_throw_a")
        }


        add_3_b.setOnClickListener { view: View ->
            score_b.text = bbViewModel.addPoints("B", 3)
            Log.i(TAG, "onClickListener for add_3_b")
        }

        add_2_b.setOnClickListener { view: View ->
            score_b.text = bbViewModel.addPoints("B", 2)
            Log.i(TAG, "onClickListener for add_2_b")
        }

        free_throw_b.setOnClickListener { view: View ->
            score_b.text = bbViewModel.addPoints("B", 1)
            Log.i(TAG, "onClickListener for free_throw_b")
        }

        reset.setOnClickListener { view: View ->
            bbViewModel.setScore("A", 0)
            bbViewModel.setScore("B", 0)
            score_a.text = bbViewModel.getScore("A")
            score_b.text = bbViewModel.getScore("B")
            score_a.setTextColor(Color.parseColor("#000000"))
            score_b.setTextColor(Color.parseColor("#000000"))
            makeClickable()
            winner_a.visibility = View.INVISIBLE
            winner_b.visibility = View.INVISIBLE
//            save_button.visibility = View.INVISIBLE
            bbViewModel.setIsWinner("A", false)
            bbViewModel.setIsWinner("B", false)
            bbViewModel.setIsGameOverCalled(false)
            Log.i(TAG, "onClickListener for reset")
        }

        game_over.setOnClickListener { view: View ->
            checkWinners()
            bbViewModel.setIsGameOverCalled(true)
            Log.i(TAG, "onClickListener for game_over")
        }

        save_button.setOnClickListener { view: View ->
            val intent = Intent(this, SaveActivity::class.java)
            startActivity(intent)
            Log.i(TAG, "onClickListener for save_button")
        }

        score_a.text = bbViewModel.getScore("A")
        score_b.text = bbViewModel.getScore("B")
        if(bbViewModel.getIsGameOverCalled()) {
            checkWinners()
        } else {
            makeClickable()
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, bbViewModel.currentIndex)
    }

    private fun checkWinners(): Void? {
        save_button.visibility = View.VISIBLE
        if(bbViewModel.checkWinner("A", "B") && bbViewModel.checkWinner("B", "A")){
            score_a.setTextColor(Color.parseColor("#32cd32"))
            winner_a.visibility = View.VISIBLE
            bbViewModel.setIsWinner("A", true)
            score_b.setTextColor(Color.parseColor("#32cd32"))
            winner_b.visibility = View.VISIBLE
            bbViewModel.setIsWinner("B", true)
        } else if(bbViewModel.checkWinner("A", "B")){
            score_a.setTextColor(Color.parseColor("#32cd32"))
            winner_a.visibility = View.VISIBLE
            bbViewModel.setIsWinner("A", true)
            score_b.setTextColor(Color.parseColor("#ff4500"))
        } else {
            score_a.setTextColor(Color.parseColor("#ff4500"))
            score_b.setTextColor(Color.parseColor("#32cd32"))
            winner_b.visibility = View.VISIBLE
            bbViewModel.setIsWinner("B", true)
        }
        if(bbViewModel.getIsWinner("A") || bbViewModel.getIsWinner("B")){
            makeNotClickable()
        }
        return null
    }

    private fun makeClickable(): Void? {
        add_3_a.isClickable = true
        add_2_a.isClickable = true
        free_throw_a.isClickable = true
        add_3_b.isClickable = true
        add_2_b.isClickable = true
        free_throw_b.isClickable = true
        return null
    }

    private fun makeNotClickable(): Void? {
        add_3_a.isClickable = false
        add_2_a.isClickable = false
        free_throw_a.isClickable = false
        add_3_b.isClickable = false
        add_2_b.isClickable = false
        free_throw_b.isClickable = false
        return null
    }

}
package com.example.project1

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "BBViewModel"

class BBViewModel : ViewModel() {
    private val teamA = Team("Team A", 0, false)
    private val teamB = Team("Team B", 0, false)

    fun addPoints(team: String, numPoints: Int): String {
        if(team === "A"){
            teamA.score = teamA.score + numPoints
            return teamA.score.toString()
        } else{
            teamB.score = teamB.score + numPoints
            return teamB.score.toString()
        }
    }

    fun checkWinner(team1: String, team2: String): Boolean {
        if(team1 === "A"){
            return teamA.score >= teamB.score
        } else if (team2 === "A"){
            return teamB.score >= teamA.score
        }
        return false
    }

    fun getScore(team: String): String {
        if(team === "A"){
            return teamA.score.toString()
        } else if (team === "B"){
            return teamB.score.toString()
        }
        return "-1"
    }

    fun setScore(team: String, score: Int): Void? {
        if(team === "A"){
            teamA.score = score
        } else if (team === "B"){
            teamB.score = score
        }
        return null
    }
}
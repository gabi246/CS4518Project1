package com.example.project1.com.example.project1

import com.example.project1.Team

data class Game(var teamA: Team = Team("Team A", 0, false),
                var teamB: Team = Team("Team A", 0, false)
){
    val teamAPhotoFileName
        get() = "TEAMA_IMG_$teamA.name.jpg"

    val teamBPhotoFileName
        get() = "TEAMB_IMG_$teamB.name.jpg"
}
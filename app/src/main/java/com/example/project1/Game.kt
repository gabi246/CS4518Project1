package com.example.project1.com.example.project1

import com.example.project1.Team

data class Game(var teamA: Team = Team("Team A", 0, false),
                var teamB: Team = Team("Team A", 0, false)
) {

}
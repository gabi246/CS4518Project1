package com.example.project1

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Game(@PrimaryKey val id: UUID = UUID.randomUUID(),
                var teamA: Team = Team("Team A", 0, false),
                var teamB: Team = Team("Team B", 0, false),
                var date: Date = Date(),
                var title: String = ""
) {

}

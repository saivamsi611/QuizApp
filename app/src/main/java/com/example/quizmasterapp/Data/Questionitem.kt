package com.example.quizmasterapp.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val category: String,
    val question: String,

    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,

    val correctAnswer: Int
)
@Entity(tableName = "scores")
data class Score(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val category: String,
    val score: Int,
    val totalQuestions: Int
)
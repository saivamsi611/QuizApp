package com.example.quizmasterapp.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface QuestionDao {

    @Insert
    suspend fun insertQuestion(question: Question)

    @Insert
    suspend fun insertQuestions(questions: List<Question>)

    @Update
    suspend fun updateQuestion(question: Question)

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Query("SELECT * FROM questions")
    fun getAllQuestions(): Flow<List<Question>>

    @Query("SELECT * FROM questions WHERE category = :category")
    fun getQuestionsByCategory(category: String): Flow<List<Question>>
}
@Dao
interface ScoreDao {

    @Insert
    suspend fun insertScore(score: Score)

    @Query("SELECT * FROM scores ORDER BY id DESC")
    fun getAllScores(): Flow<List<Score>>

    @Query("DELETE FROM scores")
    suspend fun deleteAllScores()
}
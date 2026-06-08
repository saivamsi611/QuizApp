package com.example.quizmasterapp.repository

import com.example.quizmasterapp.Data.Question
import com.example.quizmasterapp.Data.QuestionDao
import com.example.quizmasterapp.Data.Score
import com.example.quizmasterapp.Data.ScoreDao
import kotlinx.coroutines.flow.Flow

class QuizRepo (val dao: QuestionDao, val scoreDao: ScoreDao){
    suspend fun insertQuestions(questions: List<Question>) {
        dao.insertQuestions(questions)

    }
    suspend fun insertScore(score: Score) {
        scoreDao.insertScore(score)
    }

    fun getAllQuestions(): Flow<List<Question>> {
        return dao.getAllQuestions()
    }
    fun getQuestionsByCategory(category: String): Flow<List<Question>> {
        return dao.getQuestionsByCategory(category)
    }
    fun getAllScores(): Flow<List<Score>> {
        return scoreDao.getAllScores()
    }
    suspend fun deleteAllScores() {
        scoreDao.deleteAllScores()
    }

    suspend fun insertQuestion(question: Question) {
        dao.insertQuestion(question)
    }
    suspend fun updateQuestion(question: Question) {
        dao.updateQuestion(question)
    }
    suspend fun deleteQuestion(question: Question) {
        dao.deleteQuestion(question)
    }

}



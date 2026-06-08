package com.example.quizmasterapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizmasterapp.Data.Question
import com.example.quizmasterapp.Data.QuestionDao
import com.example.quizmasterapp.Data.QuizDatabase
import com.example.quizmasterapp.Data.Score
import com.example.quizmasterapp.Data.ScoreDao
import com.example.quizmasterapp.repository.QuizRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class myviewmodel(
    application: Application
) : AndroidViewModel(application) {

    private val dao: QuestionDao =
        QuizDatabase.getDatabase(application).questionDao()

    private val dao1: ScoreDao =
        QuizDatabase.getDatabase(application).scoreDao()

    private val repo = QuizRepo(dao, dao1)

    val questions = repo.getAllQuestions().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val scores = repo.getAllScores().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        populateInitialQuestions()
    }

    private fun populateInitialQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentQuestions = repo.getAllQuestions().first()
            if (currentQuestions.isEmpty()) {
                val initialQuestions = listOf(
                    Question(category = "Kotlin", question = "What does 'val' keyword mean?", option1 = "Variable", option2 = "Constant", option3 = "Function", option4 = "Class", correctAnswer = 2),
                    Question(category = "Kotlin", question = "Which of the following is used to handle null safely in Kotlin?", option1 = "??", option2 = "!!", option3 = "?.", option4 = "?:", correctAnswer = 3),
                    Question(category = "Kotlin", question = "Kotlin is developed by?", option1 = "Google", option2 = "JetBrains", option3 = "Microsoft", option4 = "Oracle", correctAnswer = 2),
                    Question(category = "Java", question = "Which keyword is used to create a subclass in Java?", option1 = "extends", option2 = "implements", option3 = "inherits", option4 = "sub", correctAnswer = 1),
                    Question(category = "Java", question = "What is the default value of a boolean variable in Java?", option1 = "true", option2 = "false", option3 = "null", option4 = "0", correctAnswer = 2),
                    Question(category = "Java", question = "Which of these is not a feature of Java?", option1 = "Object Oriented", option2 = "Use of pointers", option3 = "Platform Independent", option4 = "Dynamic", correctAnswer = 2),
                    Question(category = "Android", question = "What is the entry point of an Android activity?", option1 = "main()", option2 = "onCreate()", option3 = "onStart()", option4 = "onResume()", correctAnswer = 2),
                    Question(category = "Android", question = "Which file contains the app's package name and components?", option1 = "build.gradle", option2 = "MainActivity", option3 = "AndroidManifest.xml", option4 = "res/values", correctAnswer = 3),
                    Question(category = "DSA", question = "What is the time complexity of binary search?", option1 = "O(n)", option2 = "O(log n)", option3 = "O(n log n)", option4 = "O(1)", correctAnswer = 2),
                    Question(category = "DSA", question = "Which data structure works on LIFO principle?", option1 = "Queue", option2 = "Stack", option3 = "List", option4 = "Tree", correctAnswer = 2)
                )
                repo.insertQuestions(initialQuestions)
            }
        }
    }

    // Selected Category
    private val _selectedCategory = MutableStateFlow("")
    val selectedCategory = _selectedCategory.asStateFlow()

    fun setCategory(category: String) {
        _selectedCategory.value = category
    }

    // Quiz Score
    private val _score = MutableStateFlow(0)
    val score = _score.asStateFlow()

    fun increaseScore() {
        _score.value++
    }

    fun resetScore() {
        _score.value = 0
    }

    // Current Question Index
    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex = _currentQuestionIndex.asStateFlow()

    fun nextQuestion() {
        _currentQuestionIndex.value++
    }

    fun resetQuestionIndex() {
        _currentQuestionIndex.value = 0
    }

    fun resetQuiz() {
        _score.value = 0
        _currentQuestionIndex.value = 0
    }

    // Question Operations
    fun insertQuestions(questions: List<Question>) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertQuestions(questions)
        }
    }

    fun insertQuestion(question: Question) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertQuestion(question)
        }
    }

    fun updateQuestion(question: Question) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateQuestion(question)
        }
    }

    fun deleteQuestion(question: Question) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteQuestion(question)
        }
    }

    fun getQuestionsByCategory(category: String) =
        repo.getQuestionsByCategory(category)

    // Score Operations
    fun insertScore(score: Score) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertScore(score)
        }
    }

    fun deleteAllScores() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAllScores()
        }
    }
    private val _userAnswers =
        MutableStateFlow<MutableMap<Int, Int>>(mutableMapOf())

    val userAnswers = _userAnswers.asStateFlow()

    private val _totalQuestions = MutableStateFlow(0)
    val totalQuestions = _totalQuestions.asStateFlow()

    fun setTotalQuestions(count: Int) {
        _totalQuestions.value = count
    }
}
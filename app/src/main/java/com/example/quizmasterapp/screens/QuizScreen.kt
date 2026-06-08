package com.example.quizmasterapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quizmasterapp.nav.Routes
import com.example.quizmasterapp.viewmodel.myviewmodel

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    viewmodel: myviewmodel,
    navController: NavHostController
) {

    val category by viewmodel.selectedCategory.collectAsState()

    val questions by viewmodel
        .getQuestionsByCategory(category)
        .collectAsState(initial = emptyList())

    val currentIndex by viewmodel.currentQuestionIndex.collectAsState()

    var selectedAnswer by remember {
        mutableStateOf(-1)
    }

    if (questions.isEmpty()) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        return
    }

    val question = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Question ${currentIndex + 1}/${questions.size}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = question.question,
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        val options = listOf(
            question.option1,
            question.option2,
            question.option3,
            question.option4
        )

        options.forEachIndexed { index, option ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = selectedAnswer == index + 1,
                    onClick = {
                        selectedAnswer = index + 1
                    }
                )

                Text(
                    text = option,
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                if (selectedAnswer == question.correctAnswer) {
                    viewmodel.increaseScore()
                }

                selectedAnswer = -1

                if (currentIndex < questions.size - 1) {

                    viewmodel.nextQuestion()

                } else {

                    navController.navigate(Routes.ResultRoute)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                if (currentIndex == questions.size - 1)
                    "Finish Quiz"
                else
                    "Next Question"
            )
        }
    }
}
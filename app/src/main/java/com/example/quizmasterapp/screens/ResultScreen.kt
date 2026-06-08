package com.example.quizmasterapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quizmasterapp.nav.Routes
import com.example.quizmasterapp.Data.Score
import com.example.quizmasterapp.viewmodel.myviewmodel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    viewmodel: myviewmodel,
    navController: NavHostController
) {

    val score by viewmodel.score.collectAsState()
    val category by viewmodel.selectedCategory.collectAsState()

    val questions by viewmodel
        .getQuestionsByCategory(category)
        .collectAsState(initial = emptyList())

    val totalQuestions = questions.size

    val percentage =
        if (totalQuestions > 0)
            (score * 100) / totalQuestions
        else
            0

    LaunchedEffect(Unit) {
        viewmodel.insertScore(
            Score(
                category = category,
                score = score,
                totalQuestions = totalQuestions
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Quiz Result")
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Quiz Completed 🎉",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Final Score",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "$score / $totalQuestions",
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Percentage : $percentage%"
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate(Routes.ReviewRoute)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Review Answers")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    navController.navigate(Routes.ScoreHistoryRoute)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Score History")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {

                    viewmodel.resetQuiz()

                    navController.navigate(Routes.CategoryRoute) {
                        popUpTo(Routes.HomeRoute)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Play Again")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {

                    viewmodel.resetQuiz()

                    navController.navigate(Routes.HomeRoute) {
                        popUpTo(Routes.HomeRoute) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go Home")
            }
        }
    }
}
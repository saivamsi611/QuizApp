package com.example.quizmasterapp.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.quizmasterapp.nav.Routes
import com.example.quizmasterapp.viewmodel.myviewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    modifier: Modifier = Modifier,
    viewmodel: myviewmodel,
    navController: NavHostController
) {

    val category by viewmodel.selectedCategory.collectAsState()

    val questions by viewmodel
        .getQuestionsByCategory(category)
        .collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Review Answers")
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            items(questions) { question ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = question.question,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        val correctAnswer = when (question.correctAnswer) {
                            1 -> question.option1
                            2 -> question.option2
                            3 -> question.option3
                            4 -> question.option4
                            else -> "N/A"
                        }

                        Text(
                            text = "Correct Answer:",
                            fontWeight = FontWeight.Bold
                        )

                        Text(text = correctAnswer)
                    }
                }
            }

            item {

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate(Routes.HomeRoute) {
                            popUpTo(Routes.HomeRoute) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Back To Home")
                }
            }
        }
    }
}

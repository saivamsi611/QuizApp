package com.example.quizmasterapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.quizmasterapp.nav.Routes
import com.example.quizmasterapp.viewmodel.myviewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreHistoryScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewmodel: myviewmodel
) {

    val scores by viewmodel.scores.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Score History")
                }
            )
        }
    ) { paddingValues ->

        if (scores.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "No Quiz History Found"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text("Go Back")
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {

                items(scores) { score ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Category: ${score.category}",
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = "Score: ${score.score}/${score.totalQuestions}"
                            )

                            val percentage =
                                (score.score * 100) / score.totalQuestions

                            Text(
                                text = "Percentage: $percentage%"
                            )
                        }
                    }
                }

                item {

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            viewmodel.deleteAllScores()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Clear History")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedButton(
                        onClick = {
                            navController.navigate(Routes.HomeRoute) {
                                popUpTo(Routes.HomeRoute)
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
}
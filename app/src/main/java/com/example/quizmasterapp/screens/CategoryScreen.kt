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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewmodel: myviewmodel
) {

    var selectedCategory by remember {
        mutableStateOf("Kotlin")
    }

    val categories = listOf(
        "Kotlin",
        "Java",
        "Android",
        "DSA"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Select Category")
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Choose a Quiz Category",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            categories.forEach { category ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    onClick = {
                        selectedCategory = category
                    }
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        RadioButton(
                            selected = selectedCategory == category,
                            onClick = {
                                selectedCategory = category
                            }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = category,
                            fontSize = 18.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {

                    viewmodel.setCategory(selectedCategory)

                    viewmodel.resetQuiz()

                    navController.navigate(Routes.QuizRoute)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = "Start Quiz",
                    fontSize = 18.sp
                )
            }
        }
    }
}
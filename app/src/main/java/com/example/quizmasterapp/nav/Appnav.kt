package com.example.quizmasterapp.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizmasterapp.screens.CategoryScreen
import com.example.quizmasterapp.screens.HomeScreen
import com.example.quizmasterapp.screens.QuizScreen
import com.example.quizmasterapp.screens.ResultScreen
import com.example.quizmasterapp.screens.ReviewScreen
import com.example.quizmasterapp.screens.ScoreHistoryScreen
import com.example.quizmasterapp.viewmodel.myviewmodel

@Composable
fun Appnav(viewmodel: myviewmodel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.HomeRoute
    ) {
        composable<Routes.HomeRoute> {
            HomeScreen(viewmodel = viewmodel, navController = navController)
        }
        composable<Routes.CategoryRoute> {
            CategoryScreen(
                viewmodel = viewmodel,
                navController = navController
            )
        }
        composable<Routes.QuizRoute> {
            QuizScreen(viewmodel = viewmodel, navController = navController)
        }
        composable<Routes.ResultRoute> {
            ResultScreen(
                viewmodel = viewmodel,
                navController = navController
            )
        }
        composable<Routes.ReviewRoute> {
            ReviewScreen(
                navController = navController,
                viewmodel = viewmodel
            )
        }
        composable<Routes.ScoreHistoryRoute> {
            ScoreHistoryScreen(
                navController = navController,
                viewmodel = viewmodel
            )
        }
    }

    }

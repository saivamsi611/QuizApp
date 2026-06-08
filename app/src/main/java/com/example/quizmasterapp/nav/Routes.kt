package com.example.quizmasterapp.nav

import kotlinx.serialization.Serializable

sealed class Routes {
     @Serializable
     object HomeRoute
@Serializable
object CategoryRoute

    @Serializable
    object QuizRoute

@Serializable
    object ResultRoute
@Serializable
object ReviewRoute
@Serializable
object ScoreHistoryRoute
}
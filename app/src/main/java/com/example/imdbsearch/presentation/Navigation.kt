package com.example.imdbsearch.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.imdbsearch.common.Constants
import com.example.imdbsearch.presentation.movie_search_screen.components.MovieSearchScreen
import com.example.imdbsearch.presentation.movie_details_screen.components.MovieDetailsScreen


@Composable
fun makeNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MovieSearchScreen.route)
    {
        composable(
            route = Screen.MovieSearchScreen.route
        ) {
            MovieSearchScreen(navController)
        }
        composable(
            route = Screen.MovieSearchScreen.route+"/{${Constants.SEARCH_TITLE}}"+"/{${Constants.YEAR}}"+"/{${Constants.TYPE}}"
        ) {
            MovieSearchScreen(navController)
        }
        composable(
            route= Screen.MovieDetailsScreen.route+"/{${Constants.ID}}"
        ){
            MovieDetailsScreen()
        }
    }
}

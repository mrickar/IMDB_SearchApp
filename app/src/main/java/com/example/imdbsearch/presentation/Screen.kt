package com.example.imdbsearch.presentation

sealed class Screen (val route:String) {
    object MovieSearchScreen:Screen("movie_search_screen")
    object MovieDetailsScreen:Screen("movie_details_screen")
}
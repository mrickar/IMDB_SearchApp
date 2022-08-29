package com.example.imdbsearch.presentation.movie_details_screen
import com.example.imdbsearch.domain.model.MovieDetails

data class MovieDetailsState(
    val movieDetailsResult: MovieDetails?=null,
    val error:String="",
    val isLoading: kotlin.Boolean =false
)
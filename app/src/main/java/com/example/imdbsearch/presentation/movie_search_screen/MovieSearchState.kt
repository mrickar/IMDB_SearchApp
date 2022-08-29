package com.example.imdbsearch.presentation.movie_search_screen

import com.example.imdbsearch.data.remote.dto.MovieSearchDto

data class MovieSearchState(
    val movieSearchResult:MovieSearchDto?=null,
    var error:String="",
    var isLoading:Boolean=false
)


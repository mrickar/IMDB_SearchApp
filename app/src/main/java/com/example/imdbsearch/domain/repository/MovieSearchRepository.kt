package com.example.imdbsearch.domain.repository

import com.example.imdbsearch.data.remote.ImdbApi
import com.example.imdbsearch.data.remote.dto.MovieDetailsDto
import com.example.imdbsearch.data.remote.dto.MovieInSearch
import com.example.imdbsearch.data.remote.dto.MovieSearchDto
import javax.inject.Inject

interface MovieSearchRepository{
    suspend fun getMovieSearch(
        searchTitle:String,
        type:String?=null,
        year:String?=null,
        pageNum:String
    ):MovieSearchDto

    fun getMovieSearchFromDatabase():List<MovieInSearch>

    fun createMovieSearchDatabase(
        movieSearchDto: MovieSearchDto
    )

}
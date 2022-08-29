package com.example.imdbsearch.domain.repository

import com.example.imdbsearch.data.remote.dto.MovieDetailsDto
import com.example.imdbsearch.domain.model.MovieDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetailsById(
        id:String
    ): MovieDetailsDto

    fun getMovieDetailsByIdFromDatabase(
        id:String
    ): MovieDetails?

    fun addMovieDetailsToDatabase(
        movieDetails: MovieDetails
    )

}
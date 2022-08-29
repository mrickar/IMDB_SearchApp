package com.example.imdbsearch.data.repository

import com.example.imdbsearch.data.local.dao.MovieDetailsDao
import com.example.imdbsearch.data.remote.ImdbApi
import com.example.imdbsearch.data.remote.dto.MovieDetailsDto
import com.example.imdbsearch.domain.model.MovieDetails
import com.example.imdbsearch.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val api: ImdbApi,
    private val dao:MovieDetailsDao
):MovieDetailsRepository {
    val PATH="M:\\staj\\INNOVA\\IMDBSearch\\app\\src\\main\\java\\com\\example\\imdbsearch\\data\\repository\\MovieDetailsRepositoryImpl.kt"
    override suspend fun getMovieDetailsById(
        id:String
    ):MovieDetailsDto
    {
        return api.getMovieDetailsById(id)
    }

    override fun getMovieDetailsByIdFromDatabase(id: String): MovieDetails? {
        return dao.getMovieDetailsById(id)
    }

    override fun addMovieDetailsToDatabase(movieDetails: MovieDetails) {
        dao.addMovie(movieDetails)
    }

}
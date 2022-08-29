package com.example.imdbsearch.domain.use_case

import com.example.imdbsearch.common.Response
import com.example.imdbsearch.data.remote.dto.MovieSearchDto
import com.example.imdbsearch.domain.model.MovieDetails
import com.example.imdbsearch.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsByIdFromDatabaseUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {
    operator fun invoke(id:String): Flow<Response<MovieDetails>> {
        return flow {
            try {
                emit(Response.Loading<MovieDetails>())
                val movieDetails= repository.getMovieDetailsByIdFromDatabase(id) ?: throw Exception()
                emit(Response.Success<MovieDetails>(data = movieDetails))
            } catch (e: Exception) {
                emit(Response.Error<MovieDetails>(message=e.message?:"Couldn't find the movie."))
            }
        }
    }
}
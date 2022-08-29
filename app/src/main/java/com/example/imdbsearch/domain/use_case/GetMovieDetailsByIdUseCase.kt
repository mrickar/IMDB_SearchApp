package com.example.imdbsearch.domain.use_case

import com.example.imdbsearch.common.Response
import com.example.imdbsearch.domain.model.MovieDetails
import com.example.imdbsearch.domain.model.toMovieDetails
import com.example.imdbsearch.domain.repository.MovieDetailsRepository
import com.example.imdbsearch.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsByIdUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
)
{

    operator fun invoke(
        id:String
    ): Flow<Response<MovieDetails>> {
        return flow {
            try {
                emit(Response.Loading<MovieDetails>())
                val movieDetails = repository.getMovieDetailsById(
                    id=id
                ).toMovieDetails()
                emit(Response.Success<MovieDetails>(data = movieDetails))
            } catch (e: HttpException) {
                emit(Response.Error<MovieDetails>("An unexpected error has occured."))
            } catch (e: IOException) {
                emit(Response.Error<MovieDetails>("Couldn't reach the server."))
            } catch (e: Exception) {
                emit(Response.Error<MovieDetails>(message=e.message?:"Couldn't find the movie."))
            }
        }
    }
}
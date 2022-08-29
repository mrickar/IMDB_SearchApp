package com.example.imdbsearch.domain.use_case

import com.example.imdbsearch.common.Response

import com.example.imdbsearch.data.remote.dto.MovieSearchDto
import com.example.imdbsearch.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetMovieSearchFromDatabaseUseCase @Inject constructor(
    val repository: MovieSearchRepository
){
    operator fun invoke(): Flow<Response<MovieSearchDto>> {
        return flow {
            try {
                emit(Response.Loading<MovieSearchDto>())
                val movieSearch = repository.getMovieSearchFromDatabase()
            emit(Response.Success<MovieSearchDto>(data = MovieSearchDto(Response="True", Search = movieSearch, totalResults = movieSearch.size.toString())))
            } catch (e: IOException) {
                emit(Response.Error<MovieSearchDto>("Couldn't reach the server."))
            } catch (e: Exception) {
                emit(Response.Error<MovieSearchDto>(message=e.message?:"Couldn't find the movie."))
            }
        }
    }
}
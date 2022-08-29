package com.example.imdbsearch.domain.use_case

import com.example.imdbsearch.common.Response
import com.example.imdbsearch.data.remote.dto.MovieSearchDto
import com.example.imdbsearch.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieSearchUseCase @Inject constructor(
    private val repository: MovieSearchRepository
) {
    operator fun invoke(
        searchTitle: String,
        type: String? = null,
        year: String? = null,
        pageNum:String
    ): Flow<Response<MovieSearchDto>> {
        return flow {
            try {
                emit(Response.Loading<MovieSearchDto>())
                val card = repository.getMovieSearch(
                    searchTitle = searchTitle,
                    type = type,
                    year = year,
                    pageNum=pageNum
                )

                emit(Response.Success<MovieSearchDto>(data = card))
            } catch (e: HttpException) {
                emit(Response.Error<MovieSearchDto>("An unexpected error has occured."))
            } catch (e: IOException) {
                emit(Response.Error<MovieSearchDto>("Couldn't reach the server."))
            } catch (e: Exception) {
                println(e.message)
                emit(Response.Error<MovieSearchDto>(message=e.message?:"Couldn't find the movie."))
            }
        }
    }
}



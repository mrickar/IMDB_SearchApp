package com.example.imdbsearch.domain.use_case

import com.example.imdbsearch.data.remote.dto.MovieSearchDto
import com.example.imdbsearch.domain.repository.MovieSearchRepository
import javax.inject.Inject

class CreateMovieSearchDatabaseUseCase @Inject constructor(
    private val repository: MovieSearchRepository
) {

    operator fun invoke(
        movieSearchDto: MovieSearchDto
    ) {
        repository.createMovieSearchDatabase(movieSearchDto = movieSearchDto)
    }
}
package com.example.imdbsearch.domain.use_case

import com.example.imdbsearch.domain.model.MovieDetails
import com.example.imdbsearch.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class AddNewMovieDetailsToDatabaseUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {
    operator fun invoke(movieDetails:MovieDetails)
    {
        repository.addMovieDetailsToDatabase(movieDetails = movieDetails)
    }
}
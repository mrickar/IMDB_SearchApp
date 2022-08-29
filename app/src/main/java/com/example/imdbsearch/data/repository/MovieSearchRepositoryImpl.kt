package com.example.imdbsearch.data.repository

import com.example.imdbsearch.data.local.dao.MovieInSearchDao
import com.example.imdbsearch.data.remote.ImdbApi
import com.example.imdbsearch.data.remote.dto.MovieDetailsDto
import com.example.imdbsearch.data.remote.dto.MovieInSearch
import com.example.imdbsearch.data.remote.dto.MovieSearchDto
import com.example.imdbsearch.domain.repository.MovieSearchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(
    private val api:ImdbApi,
    private val movieInSearchDao: MovieInSearchDao
):MovieSearchRepository{
    override suspend fun getMovieSearch(
        searchTitle:String,
        type:String?,
        year:String?,
        pageNum:String
    ):MovieSearchDto
    {
        return api.getMovieSearch(search = searchTitle, type = type, year = year, pageNum = pageNum)
    }

    override fun getMovieSearchFromDatabase(): List<MovieInSearch> {
        return movieInSearchDao.getAllMovies()
    }

    override fun createMovieSearchDatabase(movieSearchDto: MovieSearchDto){
        CoroutineScope(Dispatchers.Main).launch {
            movieInSearchDao.deleteAll()
            movieInSearchDao.insertAll(*movieSearchDto.Search.toTypedArray())
        }

    }


}
package com.example.imdbsearch.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.imdbsearch.data.remote.dto.MovieInSearch

@Dao
interface MovieInSearchDao {

    @Insert
    fun insertAll(vararg movieInSearch: MovieInSearch)

    @Insert
    fun addMovie(movieInSearch: MovieInSearch)

    @Delete
    fun deleteASearch(movieInSearch: MovieInSearch)

    @Query("DELETE FROM movie_in_search")
    fun deleteAll()

    @Query("SELECT * FROM movie_in_search")
    fun getAllMovies():List<MovieInSearch>


}
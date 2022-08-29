package com.example.imdbsearch.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.imdbsearch.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailsDao {

    @Insert
    fun insertAll(vararg movieDetails: MovieDetails)

    @Insert
    fun addMovie(movieDetails: MovieDetails)

    @Delete
    fun delete(movieDetails: MovieDetails)

    @Query("SELECT * FROM movie_details WHERE imdb_id =:id")
    fun getMovieDetailsById(id:String):MovieDetails?


}
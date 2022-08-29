package com.example.imdbsearch.data.remote

import com.example.imdbsearch.data.remote.dto.MovieDetailsDto
import com.example.imdbsearch.data.remote.dto.MovieSearchDto
import retrofit2.http.GET
import retrofit2.http.Query


interface ImdbApi {

    @GET("/")
    suspend fun getMovieSearch(
        @Query("s") search:String,
        @Query("type") type:String?,//movie series episode
        @Query("y") year:String?,
        @Query("page") pageNum:String?,
    ):MovieSearchDto

    @GET("/")
    suspend fun getMovieDetailsById(
        @Query("i") id:String,
    ):MovieDetailsDto
}
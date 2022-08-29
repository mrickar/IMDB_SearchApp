package com.example.imdbsearch.data.remote.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_in_search")
data class MovieInSearch(
    @ColumnInfo
    val Poster: String,
    @ColumnInfo
    val Title: String,
    @ColumnInfo
    val Type: String,
    @ColumnInfo
    val Year: String,

    @PrimaryKey
    @ColumnInfo
    val imdbID: String
)
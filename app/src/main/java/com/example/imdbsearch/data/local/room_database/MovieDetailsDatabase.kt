package com.example.imdbsearch.data.local.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.imdbsearch.data.local.dao.MovieDetailsDao
import com.example.imdbsearch.data.local.room_database.Converters
import com.example.imdbsearch.domain.model.MovieDetails


@Database(entities = [(MovieDetails::class)], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDetailsDatabase: RoomDatabase() {

    abstract fun movieDetailsDao(): MovieDetailsDao
}
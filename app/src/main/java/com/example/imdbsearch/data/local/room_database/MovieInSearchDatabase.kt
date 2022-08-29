package com.example.imdbsearch.data.local.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imdbsearch.data.local.dao.MovieInSearchDao
import com.example.imdbsearch.data.remote.dto.MovieInSearch

@Database(entities = [(MovieInSearch::class)], version = 1)
abstract class MovieInSearchDatabase:RoomDatabase() {

    abstract fun movieInSearchDao():MovieInSearchDao
}


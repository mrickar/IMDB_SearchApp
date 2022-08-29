package com.example.imdbsearch.data.local.room_database

import androidx.room.TypeConverter
import com.example.imdbsearch.data.remote.dto.Rating
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromRating(ratings:List<Rating?>):String?
    {
        return Gson().toJson(ratings)
    }

    @TypeConverter
    fun fromStringToRating(rateJson:String?):Rating?
    {
        if(rateJson==null) return null
        return Gson().fromJson(rateJson, Rating::class.java)
    }
}
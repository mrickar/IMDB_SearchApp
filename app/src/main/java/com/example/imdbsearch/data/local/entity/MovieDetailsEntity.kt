//package com.example.imdbsearch.data.local.entity
//
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.Ignore
//import androidx.room.PrimaryKey
//import com.example.imdbsearch.data.remote.dto.Rating
//import com.example.imdbsearch.domain.model.MovieDetails
//
//@Entity(tableName = "movie_details")
//data class MovieDetailsEntity (
//    @PrimaryKey
//    @ColumnInfo(name="imdb_id")
//    val imdbID: String,
//
//    @ColumnInfo(name="Actors")
//    val Actors: String,
//
//    @ColumnInfo(name="Awards")
//    val Awards: String,
//
//    @ColumnInfo(name="Director")
//    val Director: String,
//
//    @ColumnInfo(name="Genre")
//    val Genre: String,
//
//    @ColumnInfo(name="Language")
//    val Language: String,
//
//    @ColumnInfo(name="MetaScore")
//    val MetaScore: String,
//
//    @ColumnInfo(name="Plot")
//    val Plot: String,
//
////    @ColumnInfo(name="Poster")
//    @Ignore
//    val Poster: String,
//
//    @ColumnInfo(name="Ratings")
//    val Ratings: List<Rating>,
//
//    @ColumnInfo(name="Released")
//    val Released: String,
//
//    @ColumnInfo(name="Length")
//    val Length: String,
//
//    @ColumnInfo(name="Title")
//    val Title: String,
//
//    @ColumnInfo(name="Type")
//    val Type: String,
//
//    @Ignore
//    val Year: String,
//
//    @ColumnInfo(name="imdbRating")
//    val imdbRating: String,
//    )
//
//fun MovieDetailsEntity.toMovieDetails(): MovieDetails
//{
//    return MovieDetails(
//        Actors=Actors,
//        Awards=Awards,
//        Director=Director,
//        Genre=Genre,
//        Language=Language,
//        MetaScore=MetaScore,
//        Plot=Plot,
//        Poster=Poster,
//        Ratings=Ratings,
//        Released=Released,
//        Length=Length,
//        Title=Title,
//        Type=Type,
//        Year=Year,
//        imdbID=imdbID,
//        imdbRating=imdbRating,
//    )
//}
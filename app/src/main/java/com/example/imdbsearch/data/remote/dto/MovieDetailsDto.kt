package com.example.imdbsearch.data.remote.dto

data class MovieDetailsDto(
    val Actors: String,
    val Awards: String,
    val BoxOffice: String,
    val Country: String,
    val DVD: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Plot: String,
    val Poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Response: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    val Year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
)

//fun MovieDetailsDto.toMovieDetails(): MovieDetails {
//    return MovieDetails(
//        Actors=Actors,
//        Awards=Awards,
//        Director=Director,
//        Genre=Genre,
//        Language=Language,
//        MetaScore=Metascore,
//        Plot=Plot,
//        Poster=Poster,
//        Ratings=Ratings,
//        Released=Released,
//        Length=Runtime,
//        Title=Title,
//        Type=Type,
//        Year=Year,
//        imdbID=imdbID,
//        imdbRating=imdbRating,
//    )
//}
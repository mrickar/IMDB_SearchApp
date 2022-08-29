package com.example.imdbsearch.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.imdbsearch.data.remote.dto.MovieDetailsDto
import com.example.imdbsearch.data.remote.dto.Rating
import org.intellij.lang.annotations.Language

@Entity(tableName = "movie_details")
data class MovieDetails(

    @ColumnInfo val Actors: String,
    @ColumnInfo val Awards: String,
    @ColumnInfo val Director: String,
    @ColumnInfo val Genre: String,
    @ColumnInfo val Language: String,
    @ColumnInfo val MetaScore: String,
    @ColumnInfo val Plot: String,
    @ColumnInfo val Poster: String,
    @Ignore var Ratings: List<Rating>,
    @ColumnInfo val Released: String,
    @ColumnInfo val Length: String,
    @ColumnInfo val Title: String,
    @ColumnInfo val Type: String,
    @ColumnInfo val Year: String,
    @PrimaryKey @ColumnInfo(name="imdb_id") val imdbID: String,
    @ColumnInfo val imdbRating: String,
)
{
    constructor(movieDetailsDto: MovieDetailsDto) : this(
        Actors=movieDetailsDto.Actors,
        Awards=movieDetailsDto.Awards,
        Director=movieDetailsDto.Director,
        Genre=movieDetailsDto.Genre,
        Language =movieDetailsDto.Language,
        MetaScore=movieDetailsDto.Metascore,
        Plot=movieDetailsDto.Plot,
        Poster=movieDetailsDto.Poster,
        Ratings=movieDetailsDto.Ratings,
        Released=movieDetailsDto.Released,
        Length=movieDetailsDto.Runtime,
        Title=movieDetailsDto.Title,
        Type=movieDetailsDto.Type,
        Year=movieDetailsDto.Year,
        imdbID=movieDetailsDto.imdbID,
        imdbRating=movieDetailsDto.imdbRating,
    )
    constructor(Actors: String,Awards: String,Director: String,Genre: String,Language: String,MetaScore: String,Poster: String,Plot: String,
    Released: String,Length: String,Title: String,Type: String,Year: String,imdbID: String,imdbRating: String):this(
        Actors = Actors,
        Awards = Awards,
        Director = Director,
        Genre = Genre,
        Language = Language,
        MetaScore = MetaScore,
        Plot = Plot,
        Poster = Poster,
        Ratings = listOf(),
        Released = Released,
        Length = Length,
        Title = Title,
        Type = Type,
        Year = Year,
        imdbID = imdbID,
        imdbRating = imdbRating,
    )
}
fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        Actors = Actors,
        Awards = Awards,
        Director = Director,
        Genre = Genre,
        Language = Language,
        MetaScore = Metascore,
        Plot = Plot,
        Poster = Poster,
        Ratings = Ratings,
        Released = Released,
        Length = Runtime,
        Title = Title,
        Type = Type,
        Year = Year,
        imdbID = imdbID,
        imdbRating = imdbRating,
    )
}


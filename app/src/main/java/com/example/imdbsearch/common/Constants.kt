package com.example.imdbsearch.common

import com.example.imdbsearch.data.remote.dto.MovieInSearch
import com.example.imdbsearch.data.remote.dto.Rating
import com.example.imdbsearch.domain.model.MovieDetails

object Constants {
    const val API_KEY="YOUR API KEY"
    const val BASE_URL="https://www.omdbapi.com/"
    const val ID="i"
    const val TYPE="type"
    const val SEARCH_TITLE="s"
    const val YEAR="y"
    const val MOVIE="movie"
    const val SERIES="series"

    val MOVIEINSEARCH_EXP=MovieInSearch(Poster="https://m.media-amazon.com/images/M/MV5BYzM1ZTc3MDctNzRiYS00YjU2LWJmZTYtMmQzMDZjM2E4Mzg0XkEyXkFqcGdeQXVyMTkwMjgwNzY@._V1_SX300.jpg", Title="Crocotires Traction AAA", Type="movie", Year="2000", imdbID="tt0286565")
    val MOVIE_DETAILS_EXP= MovieDetails(Actors="Carry Tefsen, Sjoukje Hooymaayer, John Leddy", Awards="Carry Tefsen, Sjoukje Hooymaayer, John Leddy", Director="N/A", Genre="Comedy", Language="Dutch", MetaScore="N/A", Plot="Sitcom related to the daily life of Dr. Lydia van der Ploeg, her children Gert-Jan and Nancy, her surgeon boyfriend Hans Lansberg, and her close neighbors.", Poster="https://m.media-amazon.com/images/M/MV5BNDEyZmE2MTgtNzZiYy00NWIwLWFjZWEtMjZjNDk4MjVhYmNlXkEyXkFqcGdeQXVyNjUyMTgxNjA@._V1_SX300.jpg", Ratings= listOf<Rating>(
        Rating(Source="Internet Movie Database", Value="6.6/10")
    ), Released="01 Jan 1981", Length="23 min", Title="Zeg 'ns Aaa", Type="series", Year="1981–2010", imdbID="tt0062614", imdbRating="6.6")
}
package com.example.imdbsearch.data.remote.dto

data class MovieSearchDto(
    val Response: String,
    val Search: List<MovieInSearch>,
    val totalResults: String
)

operator fun MovieSearchDto.plus(other:MovieSearchDto): MovieSearchDto {
    return MovieSearchDto(Response = Response, totalResults = totalResults, Search = Search+other.Search)
}
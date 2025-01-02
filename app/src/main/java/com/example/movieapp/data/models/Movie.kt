package com.example.movieapp.data.models

data class MovieResponse(
    val results: List<Movie>,
    val page: Int,
    val total_pages: Int
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String
)

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val genres: List<Genre>,
    val trailers: List<Trailer>
)

package com.example.movieapp.data.models

data class TrailerResponse(
    val results: List<Trailer>
)

data class Trailer(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val trailerUrl: String?
)

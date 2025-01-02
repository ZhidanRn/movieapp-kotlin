package com.example.movieapp.data.repository

import com.example.movieapp.data.api.TMDBClient
import com.example.movieapp.data.models.*

class MovieRepository {
    private val api = TMDBClient.api

    suspend fun getGenres(): List<Genre> = api.getGenres().genres

    suspend fun getMoviesByGenre(genreId: Int, page: Int): List<Movie> =
        api.discoverMoviesByGenre(genreId = genreId, page = page).results

    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val movieDetails = api.getMovieDetails(movieId)

        val trailers = getMovieTrailers(movieId)

        return movieDetails.copy(trailers = trailers)
    }

    suspend fun getMovieReviews(movieId: Int, page: Int): List<Review> =
        api.getMovieReviews(movieId, page = page).results

    suspend fun getMovieTrailers(movieId: Int): List<Trailer> =
        api.getMovieTrailers(movieId).results
}
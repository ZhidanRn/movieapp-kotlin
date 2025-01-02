package com.example.movieapp.data.api

import com.example.movieapp.data.models.GenreResponse
import com.example.movieapp.data.models.MovieDetails
import com.example.movieapp.data.models.MovieResponse
import com.example.movieapp.data.models.ReviewResponse
import com.example.movieapp.data.models.TrailerResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "977876387c1054938d39a67119b1b180"
const val BASE_URL = "https://api.themoviedb.org/3/"

interface TMDBApi {

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String = API_KEY): GenreResponse

    @GET("discover/movie")
    suspend fun discoverMoviesByGenre(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDetails

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): ReviewResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): TrailerResponse
}

object TMDBClient {
    val api: TMDBApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDBApi::class.java)
    }
}

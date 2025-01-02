package com.example.movieapp.data.models

data class ReviewResponse(
    val results: List<Review>,
    val page: Int,
    val total_pages: Int
)

data class Review(
    val id: String,
    val author: String,
    val content: String,
    val created_at: String
)

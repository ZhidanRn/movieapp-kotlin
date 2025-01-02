package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.MovieDetails
import com.example.movieapp.data.repository.MovieRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movie = mutableStateOf<MovieDetails?>(null)
    val movie: State<MovieDetails?> = _movie

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _movie.value = repository.getMovieDetails(movieId)
        }
    }
}
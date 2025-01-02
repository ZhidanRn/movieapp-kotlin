package com.example.movieapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController, repository: MovieRepository) {
    NavHost(navController = navController, startDestination = "genres") {
        composable("genres") {
            GenreScreen(navController = navController, repository = repository)
        }
        composable("discover/{genreId}") { backStackEntry ->
            val genreId = backStackEntry.arguments?.getString("genreId")?.toIntOrNull() ?: 0
            DiscoverScreen(navController = navController, genreId = genreId, repository = repository)
        }
        composable("movie/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: 0
            MovieDetailScreen(navController = navController, movieId = movieId, repository = repository)
        }
        composable("reviews/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: 0
            ReviewScreen(movieId = movieId, repository = repository)
        }
    }
}

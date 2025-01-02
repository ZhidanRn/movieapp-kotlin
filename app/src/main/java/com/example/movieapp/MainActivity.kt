package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.ui.navigation.NavGraph

class MainActivity : ComponentActivity() {
    private val repository = MovieRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavGraph(navController = navController, repository = repository)
        }
    }
}

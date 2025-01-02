package com.example.movieapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.data.models.Genre
import com.example.movieapp.data.repository.MovieRepository

@Composable
fun GenreScreen(navController: NavController, repository: MovieRepository) {
    var genres by remember { mutableStateOf<List<Genre>>(emptyList()) }

    LaunchedEffect(Unit) {
        genres = repository.getGenres()
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {
        genres.forEach { genre ->
            GenreCard(genre = genre, navController = navController)
        }
    }
}

@Composable
fun GenreCard(genre: Genre, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { navController.navigate("discover/${genre.id}") },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = genre.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

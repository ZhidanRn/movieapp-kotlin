package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.CircularProgressIndicator
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.ui.components.MovieCard
import kotlinx.coroutines.launch

@Composable
fun DiscoverScreen(navController: NavController, genreId: Int, repository: MovieRepository) {
    var movies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var page by remember { mutableIntStateOf(1) }
    var isLoading by remember { mutableStateOf(false) }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(page) {
        isLoading = true
        val newMovies = repository.getMoviesByGenre(genreId, page)
        movies = movies + newMovies
        isLoading = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(movies) { movie ->
                MovieCard(movie) {
                    navController.navigate("movie/${movie.id}")
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Loading Indicator at the bottom
            if (isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFF6200EE))
                    }
                }
            }
        }

        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                .collect { lastVisibleItem ->
                    if (lastVisibleItem == movies.size - 1 && !isLoading) {
                        coroutineScope.launch {
                            page += 1
                        }
                    }
                }
        }

        if (movies.isEmpty() && !isLoading) {
            Text(
                text = "No movies found.",
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

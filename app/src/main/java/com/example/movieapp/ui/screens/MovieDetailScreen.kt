package com.example.movieapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.ui.components.YouTubeVideoPlayer
import com.example.movieapp.ui.viewmodel.MovieDetailViewModel
import com.example.movieapp.ui.viewmodel.MovieDetailViewModelFactory

@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieId: Int,
    repository: MovieRepository
) {
    val viewModel: MovieDetailViewModel = viewModel(factory = MovieDetailViewModelFactory(repository))

    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }

    val movie = viewModel.movie.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movie Details") },
                backgroundColor = Color(0xFF6200EE),
                contentColor = Color.White,
                elevation = 8.dp
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                movie?.let { movieDetails ->
                    item {
                        // Trailer Section
                        if (movieDetails.trailers.isNotEmpty()) {
                            val videoId = movieDetails.trailers[0].key
                            YouTubeVideoPlayer(
                                videoId = videoId,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(16 / 9f)
                                    .padding(bottom = 16.dp)
                            )
                        } else {
                            Text(
                                text = "No trailer available",
                                color = Color.Gray,
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                        }
                    }

                    item {
                        // Movie Info Section
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            elevation = 4.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = movieDetails.title,
                                    style = MaterialTheme.typography.h6.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    ),
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )

                                Divider(
                                    color = Color.LightGray,
                                    thickness = 1.dp,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )

                                // Display Genre
                                if (movieDetails.genres.isNotEmpty()) {
                                    val genreNames = movieDetails.genres.joinToString(", ") { it.name }
                                    Text(
                                        text = "Genres: $genreNames",
                                        style = MaterialTheme.typography.body2,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )
                                }

                                // Display Release Date
                                Text(
                                    text = "Release Date: ${movieDetails.release_date}",
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )

                                Text(
                                    text = movieDetails.overview,
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Justify,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))

                        // Review Button
                        Button(
                            onClick = { navController.navigate("reviews/$movieId") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EE))
                        ) {
                            Text(
                                text = "View Reviews",
                                color = Color.White,
                                style = MaterialTheme.typography.button,
                                fontSize = 16.sp
                            )
                        }
                    }
                } ?: run {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Color(0xFF6200EE)
                        )
                    }
                }
            }
        }
    }
}
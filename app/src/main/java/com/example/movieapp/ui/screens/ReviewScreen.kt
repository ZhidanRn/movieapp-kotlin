package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.data.models.Review
import com.example.movieapp.data.repository.MovieRepository

@Composable
fun ReviewScreen(movieId: Int, repository: MovieRepository) {
    var reviews by remember { mutableStateOf<List<Review>>(emptyList()) }
    val page by remember { mutableIntStateOf(1) }

    LaunchedEffect(page) {
        reviews = repository.getMovieReviews(movieId, page)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movie Reviews", fontWeight = FontWeight.Bold) },
                backgroundColor = Color(0xFF6200EE),
                contentColor = Color.White,
                elevation = 8.dp
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "User  Reviews",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold, fontSize = 22.sp),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            if (reviews.isEmpty()) {
                item {
                    Text(
                        text = "No reviews available.",
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            } else {
                items(reviews) { review ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = 4.dp,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Author: ${review.author}",
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(bottom = 8.dp))
                            Text(
                                text = review.content,
                                style = MaterialTheme.typography.body2,
                                lineHeight = 22.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
# MovieApp

MovieApp is a native Android application that allows users to explore movie information, including trailers and reviews. The app uses **The Movie Database (TMDb) API** to fetch movie data and display YouTube trailers directly on the movie detail screen.

## Features

- Display a list of official movie genres.
- Display movies based on the selected genre.
- Show detailed information about the movie, including title and overview.
- Play the YouTube trailer directly on the movie detail screen.
- Show user reviews for each movie.
- Implement **endless scrolling** for both movie lists and user reviews.

## Technologies

- **Kotlin**: The primary programming language used for Android development.
- **Jetpack Compose**: The modern UI toolkit for Android.
- **The Movie Database (TMDb) API**: API used to fetch movie data, genres, and trailers.
- **YouTube Android Player API**: Used to play the movie trailers directly within the app.
- **Coroutines** and **Flow** for asynchronous data management.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/ZhidanRn/movieapp-kotlin.git
    ```
2. Open the project in Android Studio.
3. Build and run the app.

## Usage

1.Once the app is running, you will see the main screen displaying a list of available genres.
2.Select a genre to view the list of movies associated with that genre.
3.Click on a movie to view its details, including the overview and YouTube trailer.
4.Click the "View Reviews" button to see user reviews for the selected movie.

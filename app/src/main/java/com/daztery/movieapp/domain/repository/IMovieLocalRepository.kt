package com.daztery.movieapp.domain.repository

import com.daztery.movieapp.domain.model.Movie

interface IMovieLocalRepository {
  
  suspend fun insertFavoriteMovie(movie: Movie)
  suspend fun deleteFavoriteMovie(movie: Movie)
  suspend fun getFavoriteMovies(): List<Movie>
  suspend fun getMovies(): List<Movie>

}
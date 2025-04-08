package com.daztery.movieapp.domain.repository

import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.model.MovieDetail

interface IMovieRemoteRepository {
  
  suspend fun getMovies(): List<Movie>
  suspend fun getMovieDetails(movieId: String): MovieDetail
  suspend fun getNowPlayingMovies(): List<Movie>
  
}
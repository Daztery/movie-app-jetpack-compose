package com.daztery.movieapp.domain.repository

import com.daztery.movieapp.data.local.FavoriteMovieEntity
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
  
  // Local
  suspend fun insertFavoriteMovie(movieEntity: FavoriteMovieEntity)
  suspend fun deleteFavoriteMovie(movieEntity: FavoriteMovieEntity)
  suspend fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
  suspend fun getMoviesFromDatabase(): List<FavoriteMovieEntity>
  
  // Remote
  suspend fun getMovies(): List<Movie>  // TODO: Add parameter to difference between database and remote
  suspend fun getMovieDetails(movieId: String): MovieDetail
  suspend fun getNowPlayingMovies(): List<Movie>
  
}
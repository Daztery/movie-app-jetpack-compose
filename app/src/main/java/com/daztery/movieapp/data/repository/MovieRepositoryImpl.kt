package com.daztery.movieapp.data.repository

import com.daztery.movieapp.data.local.FavoriteMovieDao
import com.daztery.movieapp.data.local.FavoriteMovieEntity
import com.daztery.movieapp.data.remote.MovieDBAPI
import com.daztery.movieapp.data.toFavoriteMovieEntity
import com.daztery.movieapp.data.toMovieDetail
import com.daztery.movieapp.data.toMovieList
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.model.MovieDetail
import com.daztery.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
  private val movieDBAPI: MovieDBAPI,
  private val movieDao: FavoriteMovieDao,
) : MovieRepository {
  override suspend fun insertFavoriteMovie(movieEntity: FavoriteMovieEntity) {
    return movieDao.insertFavoriteMovie(movieEntity)
  }
  
  override suspend fun deleteFavoriteMovie(movieEntity: FavoriteMovieEntity) {
    return movieDao.deleteFavoriteMovie(movieEntity)
  }
  
  override suspend fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
    return movieDao.getFavoriteMovies()
  }
  
  override suspend fun getMoviesFromDatabase(): List<FavoriteMovieEntity> {
    return emptyList()
  }
  
  override suspend fun getMovies(): List<Movie> {
    return movieDBAPI.getMovies().toMovieList()
  }
  
  override suspend fun getMovieDetails(movieId: String): MovieDetail {
    return movieDBAPI.getMovieDetails(movieId).toMovieDetail()
  }
  
  override suspend fun getNowPlayingMovies(): List<Movie> {
    return movieDBAPI.getNowPlayingMovies().toMovieList()
  }
  
}
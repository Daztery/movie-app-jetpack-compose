package com.daztery.movieapp.data.repository

import com.daztery.movieapp.data.remote.MovieDBAPI
import com.daztery.movieapp.data.toMovieDetail
import com.daztery.movieapp.data.toMovieList
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.model.MovieDetail
import com.daztery.movieapp.domain.repository.IMovieRemoteRepository
import javax.inject.Inject

class MovieRemoteRepository @Inject constructor(
  private val movieDBAPI: MovieDBAPI,
) : IMovieRemoteRepository {
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
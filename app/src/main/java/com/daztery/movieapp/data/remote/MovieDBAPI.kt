package com.daztery.movieapp.data.remote

import com.daztery.movieapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBAPI {
  
  @GET("3/movie/popular")
  suspend fun getMovies(
    @Query("language") language: String = "es-ES",
    @Query("page") page: Int = 1,
    @Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
  ): MoviesResultsResponse
  
  @GET("3/movie/{movie_id}")
  suspend fun getMovieDetails(
    @Path("movie_id") movieId: String,
    @Query("language") language: String = "es-ES",
    @Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
  ): MovieDetailsResponse
  
  @GET("3/movie/now_playing")
  suspend fun getNowPlayingMovies(
    @Query("page") page: Int = 1,
    @Query("language") language: String = "es-ES",
    @Query("api_key") apiKey: String = BuildConfig.MOVIE_API_KEY,
  ): MoviesResultsResponse
  
}
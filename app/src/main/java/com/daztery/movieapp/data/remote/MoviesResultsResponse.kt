package com.daztery.movieapp.data.remote

import com.squareup.moshi.Json

data class MovieDetailsResponse(
  @Json(name = "backdrop_path")
  var backdropPath: String,
  
  @Json (name = "id")
  var id: Int,
  
  @Json (name = "overview")
  var overview: String,
  
  @Json (name = "popularity")
  var popularity: Double,
  
  @Json (name = "poster_path")
  var posterPath: String,
  
  @Json (name = "release_date")
  var releaseDate: String,
  
  @Json (name = "title")
  var title: String,
  
  @Json (name = "video")
  var video: Boolean,
  
  @Json (name = "vote_average")
  var voteAverage: Double,
  
  /*@Json(name = "runtime")
  var runtime: Int,*/
)

data class MoviesResultsResponse(
  val page: Int,
  val results: List<MovieDetailsResponse>,
  @Json(name = "total_pages") val totalPages: Int,
  @Json(name = "total_results") val totalResults: Int
)

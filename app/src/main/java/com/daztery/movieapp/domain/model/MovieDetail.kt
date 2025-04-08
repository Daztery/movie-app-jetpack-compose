package com.daztery.movieapp.domain.model

data class MovieDetail(
  var backdropPath: String,
  var id: Int,
  var overview: String,
  var popularity: Double,
  var posterPath: String,
  var releaseDate: String,
  var title: String,
  var video: Boolean,
  var voteAverage: Double,
  //var runtime:Int,
  var isMovieInFavorites: Boolean = false,
)

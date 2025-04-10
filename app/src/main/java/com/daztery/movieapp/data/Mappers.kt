package com.daztery.movieapp.data

import com.daztery.movieapp.data.local.FavoriteMovieEntity
import com.daztery.movieapp.data.remote.MovieDetailsResponse
import com.daztery.movieapp.data.remote.MoviesResultsResponse
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.model.MovieDetail

const val BASE_URL = "https://image.tmdb.org/t/p/w500/"

fun FavoriteMovieEntity.toMovie(): Movie {
  return Movie(
    id = this.movieId.toInt(),
    imageUrl = this.posterPath,
  )
}

fun List<FavoriteMovieEntity>.toMovieList(): List<Movie> {
  return this.map { favoriteMovieEntity ->
    favoriteMovieEntity.toMovie()
  }
}

fun Movie.toFavoriteMovieEntity(): FavoriteMovieEntity {
  return FavoriteMovieEntity(
    movieId = this.id.toString(),
    posterPath = this.imageUrl,
  )
}

fun MovieDetail.toMovie(): Movie{
  return Movie(
    id = this.id,
    imageUrl = this.posterPath,
    title = this.title
  )
}

fun MovieDetail.toFavoriteMovieEntity(): FavoriteMovieEntity{
  return FavoriteMovieEntity(
    movieId = this.id.toString(),
    posterPath = this.posterPath,
  )
}

fun MovieDetailsResponse.toMovieDetail(): MovieDetail {
  return MovieDetail(
    id = this.id,
    title = this.title,
    posterPath = "$BASE_URL${this.posterPath}",
    overview = this.overview,
    releaseDate = this.releaseDate,
    backdropPath = "$BASE_URL${this.backdropPath}",
    popularity = this.popularity,
    video = this.video,
    voteAverage = this.voteAverage,
    //runtime = this.runtime
  )
}

fun MoviesResultsResponse.toMovieList(): List<Movie> {
  return this.results.map { movieDetail ->
    Movie(
      id = movieDetail.id,
      title = movieDetail.title,
      imageUrl = "$BASE_URL${movieDetail.posterPath}",
    )
  }
}
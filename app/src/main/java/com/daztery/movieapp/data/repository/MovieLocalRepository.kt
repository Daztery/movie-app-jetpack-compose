package com.daztery.movieapp.data.repository

import com.daztery.movieapp.data.local.FavoriteMovieDao
import com.daztery.movieapp.data.toFavoriteMovieEntity
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.repository.IMovieLocalRepository
import javax.inject.Inject

class MovieLocalRepository @Inject constructor(
  private val movieDao: FavoriteMovieDao,
) : IMovieLocalRepository {
  override suspend fun insertFavoriteMovie(movie: Movie) {
    movieDao.insertFavoriteMovie(movie.toFavoriteMovieEntity())
  }
  
  override suspend fun deleteFavoriteMovie(movie: Movie) {
    movieDao.deleteFavoriteMovie(movie.toFavoriteMovieEntity())
  }
  
  override suspend fun getFavoriteMovies(): List<Movie> {
    return emptyList()
  }
  
  override suspend fun getMovies(): List<Movie> {
    return listOf(
      Movie(
        0,
        "Buscando a Dory",
        "https://play-lh.googleusercontent.com/eEL51k4AEWjVJ-0y-n6YmGvBj786KmGiRNraIhyUa7Zt8wAauACZ46uknVH6r_CpFJnd",
      ),
      Movie(
        1,
        "Buscando a Nemo",
        "https://es.web.img3.acsta.net/pictures/14/02/13/11/08/054573.jpg",
      ),
      Movie(
        2,
        "Aquaman",
        "https://static.cinepolis.com/resources/mx/movies/posters/414x603/44086-316627-20231219074437.jpg",
      ),
      Movie(
        3,
        "Tiburon 1",
        "https://es.web.img3.acsta.net/pictures/14/03/17/10/10/562887.jpg",
      ),
    )
  }
  
}
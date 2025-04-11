package com.daztery.movieapp.domain.usecase.movie

import com.daztery.movieapp.data.local.FavoriteMovieEntity
import com.daztery.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
  suspend operator fun invoke(): Flow<List<FavoriteMovieEntity>> {
    return repository.getFavoriteMovies()
  }
}
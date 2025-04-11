package com.daztery.movieapp.domain.usecase.movie

import com.daztery.movieapp.data.local.FavoriteMovieEntity
import com.daztery.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(private val repository: MovieRepository) {
  suspend operator fun invoke(movieEntity: FavoriteMovieEntity) {
    return repository.deleteFavoriteMovie(movieEntity)
  }
}
package com.daztery.movieapp.domain.usecase.movie

import com.daztery.movieapp.domain.model.MovieDetail
import com.daztery.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository) {
  suspend operator fun invoke(movieId: String): MovieDetail {
    return repository.getMovieDetails(movieId)
  }
}
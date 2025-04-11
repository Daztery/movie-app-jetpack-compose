package com.daztery.movieapp.domain.usecase.movie

import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(private val repository: MovieRepository) {
  suspend operator fun invoke(page: Int): List<Movie> {
    return repository.getNowPlayingMovies(page)
  }
}
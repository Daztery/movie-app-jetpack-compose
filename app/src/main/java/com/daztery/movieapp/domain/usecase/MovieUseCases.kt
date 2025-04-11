package com.daztery.movieapp.domain.usecase

import com.daztery.movieapp.domain.usecase.movie.DeleteFavoriteUseCase
import com.daztery.movieapp.domain.usecase.movie.GetFavoriteMoviesUseCase
import com.daztery.movieapp.domain.usecase.movie.GetMovieDetailsUseCase
import com.daztery.movieapp.domain.usecase.movie.GetMoviesUseCase
import com.daztery.movieapp.domain.usecase.movie.GetNowPlayingUseCase
import com.daztery.movieapp.domain.usecase.movie.InsertFavoriteUseCase

data class MovieUseCases(
  val getMoviesUseCase: GetMoviesUseCase,
  val getMovieDetailsUseCase: GetMovieDetailsUseCase,
  val getNowPlayingUseCase: GetNowPlayingUseCase,
  val insertFavoriteMovieUseCase: InsertFavoriteUseCase,
  val deleteFavoriteMovieUseCase: DeleteFavoriteUseCase,
  val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
)

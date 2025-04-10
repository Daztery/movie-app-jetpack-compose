package com.daztery.movieapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daztery.movieapp.common.utils.ErrorMessage
import com.daztery.movieapp.common.utils.ObjectUIState
import com.daztery.movieapp.data.repository.MovieRepositoryImpl
import com.daztery.movieapp.data.toFavoriteMovieEntity
import com.daztery.movieapp.data.toMovie
import com.daztery.movieapp.domain.model.MovieDetail
import com.daztery.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val movieRepository: MovieRepository
) : ViewModel() {
  
  private val _detailUIState = MutableStateFlow(ObjectUIState<MovieDetail>())
  val detailUIState = _detailUIState.asStateFlow()
  
  fun getMovieDetails(movieId: String) {
    viewModelScope.launch {
      try {
        _detailUIState.update {
          it.copy(
            isLoading = true,
          )
        }
        val movie = movieRepository.getMovieDetails(movieId)
        _detailUIState.update {
          it.copy(
            isLoading = false,
            data = movie,
          )
        }
        getFavoritesMovies()
      } catch (e: Exception) {
        val error = when (e) {
          is ConnectException -> ErrorMessage.INTERNET_CONNECTION
          else -> ErrorMessage.DEFAULT
        }
        _detailUIState.update {
          it.copy(
            isLoading = false,
            errorEnum = error
          )
        }
      }
    }
  }
  
  fun updateFavorites(movieDetail: MovieDetail) {
    viewModelScope.launch {
      if (movieDetail.isMovieInFavorites) {
        movieRepository.deleteFavoriteMovie(movieDetail.toFavoriteMovieEntity())
      } else {
        movieRepository.insertFavoriteMovie(movieDetail.toFavoriteMovieEntity())
      }
    }
  }
  
  private fun getFavoritesMovies() {
    viewModelScope.launch {
      movieRepository.getFavoriteMovies().collect { movieList ->
        val currentMovieDetail = _detailUIState.value.data
        currentMovieDetail?.let {
          val isMovieInFavorites = movieList.any { movie ->
            currentMovieDetail.id.toString() == movie.movieId
          }
          _detailUIState.update {
            it.copy(
              data = currentMovieDetail.copy(
                isMovieInFavorites = isMovieInFavorites
              )
            )
          }
        }
      }
    }
  }
  
  
}
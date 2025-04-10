package com.daztery.movieapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daztery.movieapp.common.utils.CollectUIState
import com.daztery.movieapp.common.utils.ErrorMessage
import com.daztery.movieapp.data.toFavoriteMovieEntity
import com.daztery.movieapp.data.toMovie
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
  private val movieRepository: MovieRepository
) : ViewModel() {
  
  private val _favoriteUIState = MutableStateFlow(CollectUIState<Movie>())
  val favoriteUIState = _favoriteUIState.asStateFlow()
  
  init {
    getMovies()
  }
  
  fun getMovies() {
    viewModelScope.launch {
      try {
        _favoriteUIState.update {
          it.copy(
            isLoading = true
          )
        }
        movieRepository.getFavoriteMovies().collect { movieList ->
          _favoriteUIState.update {
            it.copy(
              isLoading = false,
              data = movieList.map { favoriteMovieEntity ->
                favoriteMovieEntity.toMovie()
              }
            )
          }
        }
      } catch (e: Exception) {
        val error = when (e) {
          is ConnectException -> ErrorMessage.INTERNET_CONNECTION
          else -> ErrorMessage.DEFAULT
        }
        _favoriteUIState.update {
          it.copy(
            isLoading = true,
            errorEnum = error
          )
        }
      }
      
    }
  }
  
  fun deleteMovieFromFavorites(movie: Movie){
    viewModelScope.launch {
      movieRepository.deleteFavoriteMovie(movie.toFavoriteMovieEntity())
    }
  }
  
}
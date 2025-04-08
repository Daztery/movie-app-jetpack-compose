package com.daztery.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daztery.movieapp.common.utils.ErrorMessage
import com.daztery.movieapp.common.utils.CollectUIState
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.repository.IMovieLocalRepository
import com.daztery.movieapp.domain.repository.IMovieRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val movieRemoteRepository: IMovieRemoteRepository
) : ViewModel() {
  private val _homeUIState = MutableStateFlow(CollectUIState<Movie>())
  val homeUIState = _homeUIState.asStateFlow()
  
  init {
    getMovies()
  }
  
  fun getMovies() {
    viewModelScope.launch {
      try {
        _homeUIState.update {
          it.copy(
            isLoading = true
          )
        }
        delay(1.seconds)
        val movies = movieRemoteRepository.getMovies()
        _homeUIState.update {
          it.copy(
            data = movies,
            isLoading = false,
            errorEnum = null
          )
        }
      } catch (e: Exception) {
        val errorEnum = when {
          e is ConnectException -> ErrorMessage.INTERNET_CONNECTION
          else -> ErrorMessage.DEFAULT
        }
        _homeUIState.update {
          it.copy(
            isLoading = false,
            errorEnum = errorEnum
          )
        }
      }
    }
  }
  
}
package com.daztery.movieapp.presentation.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daztery.movieapp.common.utils.CollectUIState
import com.daztery.movieapp.common.utils.ErrorMessage
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
class NowPlayingViewModel @Inject constructor(
  private val movieRepository: MovieRepository
) : ViewModel() {
  
  private val _nowPlayingUIState = MutableStateFlow(CollectUIState<Movie>())
  val nowPlayingUIState = _nowPlayingUIState.asStateFlow()
  var currentPage = 1
  
  init {
    getMovies()
  }
  
  fun getMovies() {
    viewModelScope.launch {
      try {
        _nowPlayingUIState.update {
          it.copy(
            isLoading = true
          )
        }
        val movies = movieRepository.getNowPlayingMovies(currentPage)
        _nowPlayingUIState.update {
          it.copy(
            isLoading = false,
            data = it.data + movies
          )
        }
        currentPage++
      } catch (e: Exception) {
        val error = when (e) {
          is ConnectException -> ErrorMessage.INTERNET_CONNECTION
          else -> ErrorMessage.DEFAULT
        }
        _nowPlayingUIState.update {
          it.copy(
            isLoading = true,
            errorEnum = error
          )
        }
      }
    }
    
  }
  
}
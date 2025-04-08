package com.daztery.movieapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daztery.movieapp.common.utils.ErrorMessage
import com.daztery.movieapp.common.utils.CollectUIState
import com.daztery.movieapp.common.utils.ObjectUIState
import com.daztery.movieapp.data.repository.MovieRemoteRepository
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val movieRemoteRepository: MovieRemoteRepository
) : ViewModel() {
  
  private val _detailUIState = MutableStateFlow(ObjectUIState<MovieDetail>())
  val detailUIState = _detailUIState.asStateFlow()
  
  fun getMovieDetails(movieId:String) {
    viewModelScope.launch {
      try {
        _detailUIState.update {
          it.copy(
            isLoading = true,
          )
        }
        val movie = movieRemoteRepository.getMovieDetails(movieId)
        _detailUIState.update {
          it.copy(
            isLoading = false,
            data = movie,
          )
        }
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
  
  
}
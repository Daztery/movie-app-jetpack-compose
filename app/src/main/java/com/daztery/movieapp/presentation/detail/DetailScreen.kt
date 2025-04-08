package com.daztery.movieapp.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.daztery.movieapp.presentation.detail.components.DetailContent

@Composable
fun DetailScreen(
  movieId: String?,
  viewModel: DetailViewModel = hiltViewModel(),
  onBack: () -> Unit
) {
  
  LaunchedEffect(movieId) {
    viewModel.getMovieDetails(movieId.orEmpty())
  }
  
  val movieDetailUIState by viewModel.detailUIState.collectAsState()
  
  
  Box {
    if (movieDetailUIState.isLoading) {
      CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    } else {
      movieDetailUIState.data?.let {
        DetailContent(
          movie = it,
          onBack = onBack,
          onFavoriteClick = { /*movie ->
            viewModel.updateFavorites(movie)*/
          }
        )
      }
    }
    
  }
  
  
}
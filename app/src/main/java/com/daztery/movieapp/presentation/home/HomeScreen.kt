package com.daztery.movieapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.presentation.general_components.CardError
import com.daztery.movieapp.presentation.general_components.CardWithImage

@Composable
fun HomeScreen(
  onMovieClick: (Movie) -> Unit = {},
  viewModel: HomeViewModel = hiltViewModel()
) {
  
  val homeUIState by viewModel.homeUIState.collectAsState()
  
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    
    LazyVerticalStaggeredGrid(
      columns = StaggeredGridCells.Fixed(2),
      verticalItemSpacing = 5.dp,
      horizontalArrangement = Arrangement.spacedBy(5.dp),
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 2.dp, vertical = 4.dp)
    ) {
      items(homeUIState.data) { movie ->
        CardWithImage(
          movie = movie,
          onMovieClick = {
            onMovieClick(movie)
          }
        )
      }
    }
    
    if (homeUIState.isLoading) {
      CircularProgressIndicator()
    }
    if (homeUIState.errorEnum != null) {
      CardError(
        errorMessage = stringResource(id = homeUIState.errorEnum?.message ?: -1)
      ) {
        viewModel.getMovies()
      }
    }
  }
  
}
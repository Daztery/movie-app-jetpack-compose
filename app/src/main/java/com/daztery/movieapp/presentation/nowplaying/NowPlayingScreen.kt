package com.daztery.movieapp.presentation.nowplaying

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.presentation.general_components.CardError
import com.daztery.movieapp.presentation.general_components.CardWithImage

@Composable
fun NowPlayingScreen(
  viewModel: NowPlayingViewModel = hiltViewModel(),
  navigateToDetail: (Movie) -> Unit,
) {
  
  val nowPlayingUIState by viewModel.nowPlayingUIState.collectAsStateWithLifecycle()
  val movieList = nowPlayingUIState.data
  val gridState = rememberLazyStaggeredGridState()
  
  LaunchedEffect(gridState) {
    snapshotFlow { gridState.layoutInfo }
      .collect { layoutInfo ->
        
        val totalItems = layoutInfo.totalItemsCount
        val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
        
        if (lastVisibleItem >= totalItems - 4 && !nowPlayingUIState.isLoading) {
          viewModel.getMovies()
        }
      }
  }
  
  if (nowPlayingUIState.errorEnum != null) {
    CardError(
      errorMessage = stringResource(id = nowPlayingUIState.errorEnum?.message ?: -1)
    ) {
      viewModel.currentPage=1
      viewModel.getMovies()
    }
  } else {
    LazyVerticalStaggeredGrid(
      columns = StaggeredGridCells.Fixed(2),
      state = gridState,
      verticalItemSpacing = 5.dp,
      horizontalArrangement = Arrangement.spacedBy(5.dp),
      modifier = Modifier.padding(horizontal = 3.dp, vertical = 2.dp)
    ) {
      items(movieList) { movie ->
        CardWithImage(
          movie = movie,
          onMovieClick = { movieClicked ->
            navigateToDetail(movieClicked)
          }
        )
      }
      if (nowPlayingUIState.isLoading) {
        item {
          CircularProgressIndicator(
            modifier = Modifier
              .padding(16.dp)
              .size(32.dp)
          )
        }
      }
    }
    
  }
  
  
}
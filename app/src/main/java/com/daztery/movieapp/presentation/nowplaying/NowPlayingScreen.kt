package com.daztery.movieapp.presentation.nowplaying

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.presentation.general_components.CardWithImage

@Composable
fun NowPlayingScreen(
  viewModel: NowPlayingViewModel = hiltViewModel(),
  navigateToDetail: (Movie) -> Unit,
) {
  
  val nowPlayingUIState by viewModel.nowPlayingUIState.collectAsStateWithLifecycle()
  val movieList = nowPlayingUIState.data
  
  LazyVerticalStaggeredGrid(
    columns = StaggeredGridCells.Fixed(2),
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
  }
}
package com.daztery.movieapp.presentation.favorites

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.presentation.general_components.CardWithImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
  viewModel: FavoriteViewModel = hiltViewModel(),
  navigateToDetail: (Movie) -> Unit
) {
  val favoriteUIState by viewModel.favoriteUIState.collectAsStateWithLifecycle()
  val context = LocalContext.current
  
  Box(
    modifier = Modifier.fillMaxSize()
  ){
    if (favoriteUIState.data.isEmpty()) {
      Text(
        modifier = Modifier.align(Alignment.Center),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface,
        text = "No tienes ninguna pelicula, agrega una !"
      )
    } else {
      
      val movieList = favoriteUIState.data
      LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 5.dp,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
      ) {
        items(movieList, key = { movieModel ->
          movieModel.id
        }) { movie ->
          val dismissState = rememberSwipeToDismissBoxState(
            confirmValueChange = { dismissValue ->
              if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                viewModel.deleteMovieFromFavorites(movie)
                Toast.makeText(context, "Pelicula eliminada correctamente", Toast.LENGTH_SHORT)
                  .show()
                true
              } else {
                false
              }
            }
          )
          SwipeToDismissBox(
            state = dismissState,
            backgroundContent = {},
            content = {
              CardWithImage(
                movie = movie,
                onMovieClick = { movieClicked ->
                  navigateToDetail(movieClicked)
                }
              )
            }
          )
        }
      }
    }
    
    if (favoriteUIState.isLoading) {
      CircularProgressIndicator()
    }
    
    if (favoriteUIState.errorEnum != null) {
      Text(favoriteUIState.errorEnum.toString())
    }
  }
  
  
}
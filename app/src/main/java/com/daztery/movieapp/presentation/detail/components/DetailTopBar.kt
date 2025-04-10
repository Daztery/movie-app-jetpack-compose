package com.daztery.movieapp.presentation.detail.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.daztery.movieapp.domain.model.MovieDetail


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
  movieDetail: MovieDetail,
  onBack: () -> Unit,
  onFavoriteClick: (MovieDetail) -> Unit
) {
  CenterAlignedTopAppBar(
    navigationIcon = {
      IconButton(onClick = onBack) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
      }
    },
    actions = {
      IconButton(onClick = {
        onFavoriteClick(movieDetail)
      }) {
        Icon(
          imageVector = if (movieDetail.isMovieInFavorites) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
          contentDescription = null
        )
      }
    },
    title = {
      Text(text = movieDetail.title)
    }
  )
}
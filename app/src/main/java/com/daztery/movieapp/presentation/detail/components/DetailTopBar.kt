package com.daztery.movieapp.presentation.detail.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.daztery.movieapp.domain.model.MovieDetail


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(movie: MovieDetail, onBack: () -> Unit, onFavoriteClick: () -> Unit) {
  CenterAlignedTopAppBar(
    navigationIcon = {
      IconButton(onClick = onBack) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
      }
    },
    actions = {
      IconButton(onClick = onFavoriteClick) {
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
      }
    },
    title = {
      Text(text = movie.title)
    }
  )
}
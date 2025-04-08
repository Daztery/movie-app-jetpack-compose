package com.daztery.movieapp.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daztery.movieapp.domain.model.MovieDetail

@Composable
fun DetailContent(
  movie: MovieDetail,
  onBack: () -> Unit,
  onFavoriteClick: () -> Unit
) {
  Scaffold(
    topBar = {
      DetailTopBar(
        movie = movie,
        onBack = onBack,
        onFavoriteClick = onFavoriteClick
      )
    }
  ) { paddingValues ->
    Column(modifier = Modifier.padding(paddingValues)) {
      DetailHeader(
        modifier = Modifier
          .fillMaxWidth()
          .height(400.dp),
        movieDetail = movie
      )
      DetailBody(movieDetail = movie)
    }
  }
}
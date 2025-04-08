package com.daztery.movieapp.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.daztery.movieapp.domain.model.MovieDetail

@Composable
fun DetailHeader(
  modifier: Modifier = Modifier,
  movieDetail: MovieDetail
) {
  Box(modifier = modifier) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .zIndex(1f)
        .background(Color.Black.copy(alpha = 0.6f))
    )
    AsyncImage(
      modifier = Modifier
        .zIndex(0f)
        .fillMaxSize(),
      model = movieDetail.backdropPath,
      contentDescription = null,
      contentScale = ContentScale.Crop
    )
    AsyncImage(
      modifier = Modifier
        .zIndex(2f)
        .fillMaxHeight(0.9f)
        .fillMaxWidth(0.6f)
        .align(Alignment.Center)
        .clip(RoundedCornerShape(12.dp)),
      model = movieDetail.posterPath,
      contentDescription = null,
      contentScale = ContentScale.Crop
    )
  }
}
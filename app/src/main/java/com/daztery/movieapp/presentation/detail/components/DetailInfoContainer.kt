package com.daztery.movieapp.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daztery.movieapp.domain.model.MovieDetail
import com.daztery.movieapp.presentation.general_components.IconWithText

@Composable
fun DetailInfoContainer(movieDetail: MovieDetail) {
  OutlinedCard(
    shape = CircleShape,
    modifier = Modifier
      .fillMaxWidth()
      .height(100.dp)
      .padding(horizontal = 20.dp, vertical = 12.dp)
  ) {
    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxSize()
    ) {
      IconWithText(text = movieDetail.releaseDate, icon = Icons.Filled.DateRange)
      IconWithText(text = "90 mins", icon = Icons.Filled.AccessTime)
      IconWithText(text = "Pelicula", icon = Icons.Filled.LocalMovies)
    }
  }
}
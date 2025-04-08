package com.daztery.movieapp.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.daztery.movieapp.domain.model.MovieDetail

@Composable
fun DetailBody(movieDetail: MovieDetail) {
  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    DetailInfoContainer(movieDetail)
    DetailDescriptionContainer(movieDetail.overview)
  }
}
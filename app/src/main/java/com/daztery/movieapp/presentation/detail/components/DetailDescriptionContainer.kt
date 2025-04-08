package com.daztery.movieapp.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetailDescriptionContainer(description: String) {
  Column(
    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
  ) {
    Text(
      text = "Descripción de la película",
      fontWeight = FontWeight.Bold,
      style = MaterialTheme.typography.titleMedium,
      modifier = Modifier.padding(4.dp)
    )
    Text(
      text = description,
      style = MaterialTheme.typography.bodyMedium,
      modifier = Modifier.padding(4.dp)
    )
  }
}
package com.daztery.movieapp.presentation.general_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardError(errorMessage: String, onRefreshClick: () -> Unit) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
      text = errorMessage,
      maxLines = 2,
      textAlign = TextAlign.Center,
      fontSize = 24.sp
    )
    Button(
      modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp),
      onClick = onRefreshClick
    ) {
      Text(text = "Volver a cargar")
    }
  }
}
package com.daztery.movieapp.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class MainBottomScreenItem (
  val title: String,
  val route:String,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector
)
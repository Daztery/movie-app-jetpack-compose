package com.daztery.movieapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination

@Composable
fun MainBottomNavigationBar(navController: NavController, currentDestination: NavDestination?) {
  val mainScreens = listOf(
    MainBottomScreenItem(
      title = "Home",
      route = Routes.HomeScreen,
      selectedIcon = Icons.Filled.Home,
      unselectedIcon = Icons.Outlined.Home
    ),
    MainBottomScreenItem(
      title = "Favorites",
      route = Routes.FavoriteScreen,
      selectedIcon = Icons.Filled.Favorite,
      unselectedIcon = Icons.Outlined.FavoriteBorder
    ),
    MainBottomScreenItem(
      title = "NowPlaying",
      route = Routes.NowPlayingScreen,
      selectedIcon = Icons.Filled.PlayArrow,
      unselectedIcon = Icons.Outlined.PlayArrow
    ),
  )
  NavigationBar {
    mainScreens.forEach { screen ->
      NavigationBarItem(
        label = {
          Text(text = screen.title)
        },
        selected = currentDestination?.route == screen.route,
        onClick = {
          navController.navigate(screen.route) {
            popUpTo(navController.graph.startDestinationId) {
              saveState = true
            }
            launchSingleTop = true
            restoreState = true
          }
        },
        icon = {
          Icon(
            imageVector = if (currentDestination?.route == screen.route)
              screen.selectedIcon
            else
              screen.unselectedIcon,
            contentDescription = null
          )
          
        })
    }
  }
}
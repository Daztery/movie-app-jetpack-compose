package com.daztery.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.daztery.movieapp.presentation.detail.DetailScreen
import com.daztery.movieapp.presentation.favorites.FavoriteScreen
import com.daztery.movieapp.presentation.home.HomeScreen
import com.daztery.movieapp.presentation.navigation.MainBottomNavigationBar
import com.daztery.movieapp.presentation.navigation.Routes
import com.daztery.movieapp.presentation.nowplaying.NowPlayingScreen
import com.daztery.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MovieAppTheme {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry?.destination
        Scaffold(
          modifier = Modifier.fillMaxSize(),
          bottomBar = {
            AnimatedVisibility(visible = currentDestination?.route?.contains(Routes.DetailScreen) == false) {
              MainBottomNavigationBar(navController, currentDestination)
            }
          }
        ) { innerPadding ->
          NavHost(
            navController = navController,
            startDestination = Routes.HomeScreen,
            modifier = Modifier.padding(innerPadding)
          ) {
            composable(Routes.HomeScreen) {
              HomeScreen(
                onMovieClick = { movie ->
                  navController.navigate(Routes.DetailScreen + "/${movie.id}")
                }
              )
            }
            composable(
              route = Routes.DetailScreen + "/{movieId}",
              arguments = listOf(navArgument(name = "movieId") {
                type = NavType.StringType
              })
            ) {
              val movieId = it.arguments?.getString("movieId")
              DetailScreen(
                movieId = movieId,
                onBack = {
                  navController.navigateUp()
                }
              )
            }
            composable(Routes.FavoriteScreen) {
              FavoriteScreen(
                navigateToDetail = { movie ->
                  navController.navigate(Routes.DetailScreen + "/${movie.id}")
                }
              )
            }
            composable(Routes.NowPlayingScreen) {
              NowPlayingScreen(
                navigateToDetail = { movie ->
                  navController.navigate(Routes.DetailScreen + "/${movie.id}")
                }
              )
            }
          }
        }
      }
    }
  }
}

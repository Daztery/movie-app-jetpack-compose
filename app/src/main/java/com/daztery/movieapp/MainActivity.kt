package com.daztery.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.daztery.movieapp.presentation.home.HomeScreen
import com.daztery.movieapp.presentation.navigation.Routes
import com.daztery.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MovieAppTheme {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry?.destination
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          NavHost(navController = navController, startDestination = Routes.HomeScreen, modifier = Modifier.padding(innerPadding)) {
            composable(Routes.HomeScreen){
              HomeScreen()
            }
            composable(Routes.DetailScreen){
              Text("Detail")
            }
            composable(Routes.FavoriteScreen){
              Text("Favorite")
            }
            composable(Routes.NowPlayingScreen){
              Text("NwoPlaying")
            }
          }
        }
      }
    }
  }
}

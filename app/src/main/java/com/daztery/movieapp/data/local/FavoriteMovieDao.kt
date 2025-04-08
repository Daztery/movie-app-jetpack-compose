package com.daztery.movieapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteMovieDao {
  @Insert
  suspend fun insertFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)
  
  @Delete
  suspend fun deleteFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)
  
  @Query("SELECT * FROM favoritemovieentity")
  suspend fun getFavoriteMovies(): List<FavoriteMovieEntity>

}
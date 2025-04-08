package com.daztery.movieapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteMovieEntity(
  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "movie_id")
  val movieId: String,
  
  @ColumnInfo(name = "poster_path")
  val posterPath: String,
)

package com.daztery.movieapp.di

import android.app.Application
import androidx.room.Room
import com.daztery.movieapp.data.local.FavoriteMovieDao
import com.daztery.movieapp.data.local.FavoriteMovieDatabase
import com.daztery.movieapp.data.remote.MovieDBAPI
import com.daztery.movieapp.data.repository.MovieLocalRepository
import com.daztery.movieapp.data.repository.MovieRemoteRepository
import com.daztery.movieapp.domain.repository.IMovieLocalRepository
import com.daztery.movieapp.domain.repository.IMovieRemoteRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
  
  @Singleton
  @Provides
  fun provideDatabaseClass(application: Application): FavoriteMovieDatabase {
    return Room.databaseBuilder(
      application,
      FavoriteMovieDatabase::class.java,
      "favorite_movie_db"
    ).build()
  }
  
  
  @Singleton
  @Provides
  fun providesFavoriteMovieDao(favoriteMovieDatabase: FavoriteMovieDatabase): FavoriteMovieDao {
    return favoriteMovieDatabase.createFavoriteMovieDao()
  }
  
  @Singleton
  @Provides
  fun providesRetrofit(): Retrofit {
    val moshi = Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()
    
    return Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/")
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }
  
  @Singleton
  @Provides
  fun providesMovieApi(retrofit: Retrofit): MovieDBAPI {
    return retrofit.create(MovieDBAPI::class.java)
  }
  
}


@Module
@InstallIn(SingletonComponent::class)
abstract class Binds {
  
  @Binds
  abstract fun movieRemoteRepository(
    movieRemoteRepository: MovieRemoteRepository
  ): IMovieRemoteRepository
}
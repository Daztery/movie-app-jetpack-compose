package com.daztery.movieapp.di

import android.app.Application
import androidx.room.Room
import com.daztery.movieapp.BuildConfig
import com.daztery.movieapp.data.local.FavoriteMovieDao
import com.daztery.movieapp.data.local.FavoriteMovieDatabase
import com.daztery.movieapp.data.remote.MovieDBAPI
import com.daztery.movieapp.data.repository.MovieRepositoryImpl
import com.daztery.movieapp.domain.repository.MovieRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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
    
    val okHttpClient = OkHttpClient.Builder()
      .addInterceptor(ApiKeyInterceptor(BuildConfig.MOVIE_API_KEY))
      .build()
    
    return Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/")
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .client(okHttpClient)
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
  abstract fun movieRepository(
    movieRepositoryImpl: MovieRepositoryImpl
  ): MovieRepository
  
}

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()
    val originalUrl = original.url
    
    val newUrl = originalUrl.newBuilder()
      .addQueryParameter("api_key", apiKey)
      .build()
    
    val newRequest = original.newBuilder()
      .url(newUrl)
      .build()
    
    return chain.proceed(newRequest)
  }
}
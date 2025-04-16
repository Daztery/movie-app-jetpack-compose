package com.daztery.movieapp.domain.usecase

import com.daztery.movieapp.data.toFavoriteMovieEntity
import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.repository.MovieRepository
import com.daztery.movieapp.domain.usecase.movie.InsertFavoriteUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class InsertMovieUseCaseTest {
  private lateinit var movieRepository: MovieRepository
  private lateinit var insertMovieUseCase: InsertFavoriteUseCase
  
  @Before
  fun setUp() {
    movieRepository = mockk()
    insertMovieUseCase = InsertFavoriteUseCase(movieRepository)
  }
  
  @Test
  fun `should insert movie into repository`() = runTest {
    val movie = Movie(
      id = 1,
      title = "Batman",
      imageUrl = "batman.jpg"
    )
    
    coEvery { movieRepository.insertFavoriteMovie(movie.toFavoriteMovieEntity()) } returns Unit
    
    insertMovieUseCase(movie.toFavoriteMovieEntity())
    
    coVerify { movieRepository.insertFavoriteMovie(movie.toFavoriteMovieEntity()) }
    
  }
  
}
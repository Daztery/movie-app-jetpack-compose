package com.daztery.movieapp.domain.usecase

import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.repository.MovieRepository
import com.daztery.movieapp.domain.usecase.movie.GetNowPlayingUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetNowPlayingMoviesUseCaseTest {
  private lateinit var movieRepository: MovieRepository
  private lateinit var getNowPlayingUseCase: GetNowPlayingUseCase
  
  @Before
  fun setUp(){
    movieRepository = mockk()
    getNowPlayingUseCase = GetNowPlayingUseCase(movieRepository)
  }
  
  @Test
  fun `should return the same list when repository returns movies`() = runTest {
    // Given
    val expectedMovies = listOf(
      Movie(id = 1, title = "Batman", imageUrl = "batman.jpg"),
      Movie(id = 2, title = "Superman", imageUrl = "superman.jpg")
    )
    coEvery { movieRepository.getNowPlayingMovies(1) } returns expectedMovies
    
    // When
    val result = getNowPlayingUseCase(1)
    
    // Then
    assertEquals(expectedMovies, result)
    coVerify { movieRepository.getNowPlayingMovies(1) }
  }
  
  @Test
  fun `should return empty list when repository returns empty list`() = runTest {
    // Given
    coEvery { movieRepository.getNowPlayingMovies(1) } returns emptyList()
    
    // When
    val result = getNowPlayingUseCase(1)
    
    // Then
    assertEquals(emptyList<Movie>(), result)
    coVerify { movieRepository.getNowPlayingMovies(1) }
    
  }
  
  @Test(expected = Exception::class)
  fun `should throw exception when repository throws exception`() = runTest {
    // Given
    coEvery { movieRepository.getNowPlayingMovies(1) } throws Exception()
    
    // When
    getNowPlayingUseCase(1)
    
  }
  
  
}
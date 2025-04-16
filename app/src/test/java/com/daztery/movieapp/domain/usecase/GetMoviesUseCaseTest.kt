package com.daztery.movieapp.domain.usecase

import com.daztery.movieapp.domain.model.Movie
import com.daztery.movieapp.domain.repository.MovieRepository
import com.daztery.movieapp.domain.usecase.movie.GetMoviesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMoviesUseCaseTest {
  private lateinit var movieRepository: MovieRepository
  private lateinit var getMoviesUseCases: GetMoviesUseCase
  
  @Before
  fun setUp() {
    movieRepository = mockk()
    getMoviesUseCases = GetMoviesUseCase(movieRepository)
  }
  
  @Test
  fun `should usecase return the same list when repository returns movies`() = runTest {
    
    // Given
    val fakeMovies = listOf(
      Movie(id = 1, title = "Batman", imageUrl = "batman.jpg"),
      Movie(id = 2, title = "Batman2", imageUrl = "batman2.jpg"),
    )
    
    coEvery { movieRepository.getMovies(1) } returns fakeMovies
    
    // When
    val result = getMoviesUseCases(1)
    
    // Then
    assertEquals(fakeMovies, result)
    coVerify { movieRepository.getMovies(1) }

  }
  
  @Test
  fun `should usecase return empty list when repository returns empty list`() = runTest {
    
    // Given
    coEvery { movieRepository.getMovies(1) } returns emptyList()
    
    // When
    val result = getMoviesUseCases(1)
    
    // Then
    assertEquals(emptyList<Movie>(), result)
    coVerify { movieRepository.getMovies(1) }
    
  }
  
  @Test(expected = Exception::class)
  fun `should throw exception when repository throws exception`() = runTest {
    // Given
    coEvery { movieRepository.getMovies(1) } throws Exception()
    
    // When
    getMoviesUseCases(1)
  }
  
  @Test
  fun `should call repository with correct page` () = runTest {
    // Given
    coEvery { movieRepository.getMovies(2) } returns emptyList()
    
    // When
    getMoviesUseCases(2)
    
    // Then
    coVerify { movieRepository.getMovies(2) }

  }
  
}
package com.daztery.movieapp.domain.usecase

import com.daztery.movieapp.domain.model.MovieDetail
import com.daztery.movieapp.domain.repository.MovieRepository
import com.daztery.movieapp.domain.usecase.movie.GetMovieDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMovieDetailsUseCaseTest {
  private lateinit var movieRepository: MovieRepository
  private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
  
  @Before
  fun setUp() {
    movieRepository = mockk()
    getMovieDetailsUseCase = GetMovieDetailsUseCase(movieRepository)
  }
  
  @Test
  fun `should usecase return the detail when repository returns the detail too`() = runTest {
    // Given
    val movieDetail = MovieDetail(
      id = 1,
      title = "Batman",
      overview = "Batman overview",
      popularity = 1.0,
      posterPath = "",
      releaseDate = "",
      video = false,
      voteAverage = 1.0,
      backdropPath = "",
      isMovieInFavorites = false,
    )
    
    // When
    coEvery { movieRepository.getMovieDetails(movieDetail.id.toString()) } returns movieDetail
    
    // Then
    assertEquals(movieDetail, getMovieDetailsUseCase(movieDetail.id.toString()))
    coVerify { movieRepository.getMovieDetails(movieDetail.id.toString()) }

  }
  
}
package com.daztery.movieapp.common.utils

data class CollectUIState<T>(
  val data: List<T> = emptyList(),
  val isLoading: Boolean =  false,
  val errorEnum: ErrorMessage? = null,
)

data class ObjectUIState<T>(
  val data: T? = null,
  val isLoading: Boolean =  false,
  val errorEnum: ErrorMessage? = null,
)
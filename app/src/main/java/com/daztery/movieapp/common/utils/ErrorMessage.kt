package com.daztery.movieapp.common.utils

import androidx.annotation.StringRes
import com.daztery.movieapp.R

enum class ErrorMessage(@StringRes val message: Int) {
  INTERNET_CONNECTION(R.string.internet_connection_error_message),
  DATABASE_ERROR(R.string.database_connection_error_message),
  DEFAULT(R.string.default_error_message_text)
}
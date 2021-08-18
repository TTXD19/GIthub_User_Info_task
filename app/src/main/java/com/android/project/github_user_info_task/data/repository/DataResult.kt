package com.android.project.github_user_info_task.data.repository

sealed class DataResult<T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Failure<T>(val error: String = "Network Error") : DataResult<T>()
}
package com.home.firebaseflowexampledemo.model

sealed class MainViewState<T> {

    class Loading<T> : MainViewState<T>()
    data class Success<T>(val data: T) : MainViewState<T>()
    data class Failed<T>(val message: String) : MainViewState<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(message: String) = Failed<T>(message)
    }
}
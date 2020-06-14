package com.home.firebaseflowexampledemo.viewmodel

import androidx.lifecycle.ViewModel
import com.home.firebaseflowexampledemo.model.MainRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainViewModel(private val repository: MainRepository) : ViewModel() {

    fun getData() = repository.getData()
}
package com.home.firebaseflowexampledemo.model

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

@ExperimentalCoroutinesApi
class MainRepository {

    private val workCollection =
        FirebaseFirestore.getInstance().collection("work")

    fun getData() = flow<MainViewState<List<MainPojo>>> {
        emit(MainViewState.loading())
        val querySnapshot = workCollection.get().await()
        val list = querySnapshot.toObjects(MainPojo::class.java)
        emit(MainViewState.success(list))
    }
        .catch { emit(MainViewState.failed(it.message.toString())) }
        .flowOn(Dispatchers.IO)
}

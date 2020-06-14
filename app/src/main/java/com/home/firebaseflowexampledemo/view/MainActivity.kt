package com.home.firebaseflowexampledemo.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.home.firebaseflowexampledemo.common.ViewUtil
import com.home.firebaseflowexampledemo.databinding.ActivityMainBinding
import com.home.firebaseflowexampledemo.model.MainViewState
import com.home.firebaseflowexampledemo.model.MainPojo
import com.home.firebaseflowexampledemo.viewmodel.MainViewModel
import com.home.firebaseflowexampledemo.viewmodel.MainViewModelFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var mainScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewUtil.setStatusColor(this,
            isTranslate = false,
            isDarkText = true,
            bgColor = android.R.color.white
        )
        initView()
        initViewModel()
        mainScope.launch { getData() }
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MainAdapter()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, MainViewModelFactory())
            .get(MainViewModel::class.java)
        mainScope = CoroutineScope(Dispatchers.Main)
    }

    private suspend fun getData() {
        viewModel.getData().collect {
            when (it) {
                is MainViewState.Loading -> setProgress(true)
                is MainViewState.Success -> {
                    setProgress(false)
                    showSuccess(it.data.toMutableList())
                }
                is MainViewState.Failed -> {
                    setProgress(false)
                    showError("Failed! ${it.message}")
                }
            }
        }
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showSuccess(list: MutableList<MainPojo>) {
        adapter.updateList(list)
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

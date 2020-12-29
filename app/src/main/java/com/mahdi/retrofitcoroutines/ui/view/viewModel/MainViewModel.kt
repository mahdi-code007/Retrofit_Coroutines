package com.mahdi.retrofitcoroutines.ui.view.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdi.retrofitcoroutines.data.api.model.Post
import com.mahdi.retrofitcoroutines.data.api.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val mutableLiveDataResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    fun getPost() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPost()
            withContext(Dispatchers.Main) {
                mutableLiveDataResponse.value = response
            }

        }
    }
}
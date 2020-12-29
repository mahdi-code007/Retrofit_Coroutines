package com.mahdi.retrofitcoroutines.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mahdi.retrofitcoroutines.R
import com.mahdi.retrofitcoroutines.databinding.ActivityMainBinding
import com.mahdi.retrofitcoroutines.data.api.repository.Repository
import com.mahdi.retrofitcoroutines.ui.view.viewModel.MainViewModel
import com.mahdi.retrofitcoroutines.ui.view.viewModel.MainViewModelFactory

private lateinit var binding: ActivityMainBinding
private lateinit var viewModel: MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.mutableLiveDataResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                binding.postTitle.text = response.body()?.title!!
                binding.postBody.text = response.body()?.body!!
            } else {
                Toast.makeText(this, response.errorBody().toString(), Toast.LENGTH_LONG).show()
                Toast.makeText(this, response.code().toString(), Toast.LENGTH_LONG).show()
            }


        })

//        GlobalScope.launch (Dispatchers.IO) {
//
//            val response = RetrofitInstance.api.getPost()
//
//            withContext(Dispatchers.Main){
//                binding.postTitle.text = response.title
//                binding.postBody.text = response.body
//            }
//
//
//        }
    }
}
package com.mahdi.retrofitcoroutines.data.api.repository

import com.mahdi.retrofitcoroutines.data.api.model.Post
import com.mahdi.retrofitcoroutines.data.api.RetrofitInstance
import retrofit2.Response


class Repository {

    suspend fun getPost() : Response<Post>{
        return RetrofitInstance.api.getPost()
    }
}
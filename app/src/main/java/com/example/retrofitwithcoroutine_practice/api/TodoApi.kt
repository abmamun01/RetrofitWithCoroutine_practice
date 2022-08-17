package com.example.retrofitwithcoroutine_practice.api

import com.example.retrofitwithcoroutine_practice.model.Data_
import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {


    @GET("/todos")
    suspend fun getTodo(): Response<Data_>

}
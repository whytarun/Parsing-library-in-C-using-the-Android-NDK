package com.example.navvisapp.api

import com.example.navvisapp.models.NumberResponse
import retrofit2.Response
import retrofit2.http.GET

interface NavVisApi {
    @GET("/todos/1")
    suspend fun getAllNumbers():Response<NumberResponse>
}
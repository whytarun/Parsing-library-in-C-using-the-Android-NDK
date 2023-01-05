package com.example.navvisapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navvisapp.api.NavVisApi
import com.example.navvisapp.models.NumberResponse
import com.example.navvisapp.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class NavVisRepository @Inject constructor(private val navVisApi: NavVisApi) {

    suspend fun getAllNumbers(): NetworkResult<NumberResponse> {
        val response = navVisApi.getAllNumbers()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody !=null){
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error("something went wrong")
            }
        } else {
            NetworkResult.Error("something went wrong")
        }
    }

}
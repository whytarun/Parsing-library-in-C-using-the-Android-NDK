package com.example.navvisapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navvisapp.models.NumberResponse
import com.example.navvisapp.repository.NavVisRepository
import com.example.navvisapp.utils.Constants.TAG
import com.example.navvisapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val navVisRepository: NavVisRepository) :ViewModel() {

    private val _numberResponseLiveData = MutableLiveData<NetworkResult<NumberResponse>>()
    val numberResponseLiveDate: LiveData<NetworkResult<NumberResponse>>
        get() = _numberResponseLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        Log.e(TAG, error.toString())
    }
    fun getNumbers(){
        _numberResponseLiveData.value =NetworkResult.Loading()
        viewModelScope.launch(exceptionHandler) {
            _numberResponseLiveData.postValue( navVisRepository.getAllNumbers())
        }
    }
}
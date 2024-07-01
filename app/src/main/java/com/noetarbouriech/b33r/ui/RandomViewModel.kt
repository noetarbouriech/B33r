package com.noetarbouriech.b33r.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noetarbouriech.b33r.network.ApiResponse
import com.noetarbouriech.b33r.network.MyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RandomBeerViewModel : ViewModel() {
    private val _randomBeerId = MutableStateFlow<String?>(null)
    val randomBeerId: StateFlow<String?> = _randomBeerId.asStateFlow()

    fun fetchRandomBeerId() {
        viewModelScope.launch {
            try {
                val index = (1..175).random() // Generate random index between 1 and 175
                val result: ApiResponse = MyApi.retrofitService.getBeers("styleId:$index")
                _randomBeerId.value = result.hits.randomOrNull()?.id ?: ""
            } catch (e: Exception) {
                // Handle error
                _randomBeerId.value = null
            }
        }
    }
}

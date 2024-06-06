package com.noetarbouriech.b33r.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noetarbouriech.b33r.network.ApiResponse
import com.noetarbouriech.b33r.network.MyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // Expose screen UI state
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            val result: ApiResponse = MyApi.retrofitService.getData()
            _uiState.update { currentState ->
                currentState.copy(test = result.hits[(0..<result.hits.size).random()])
            }
        }
    }
}
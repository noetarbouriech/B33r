package com.noetarbouriech.b33r.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noetarbouriech.b33r.network.Beer
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

    fun incrementTest() {
        _uiState.update { currentState ->
            currentState.copy(
                test = currentState.test + 1
            )
        }
    }

    fun getData() {
        viewModelScope.launch {
            val result: List<Beer> = MyApi.retrofitService.getData()
            _uiState.update { currentState ->
                currentState.copy(test2 = result[0])
            }
        }
    }
}
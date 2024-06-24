package com.noetarbouriech.b33r.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noetarbouriech.b33r.network.ApiResponse
import com.noetarbouriech.b33r.network.MyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BeerViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    // Expose screen UI state
    private val beerId: String = checkNotNull(savedStateHandle["beerId"])
    private val _uiState = MutableStateFlow(BeerUiState())
    val uiState: StateFlow<BeerUiState> = _uiState.asStateFlow()


    private fun getData() {
        viewModelScope.launch {
            val result: ApiResponse = MyApi.retrofitService.getBeers("id:$beerId")
            _uiState.update { currentState ->
                currentState.copy(beer = result.hits[0])
            }
        }
    }

    init {
        getData()
    }

}

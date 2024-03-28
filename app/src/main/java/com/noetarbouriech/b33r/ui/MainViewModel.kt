package com.noetarbouriech.b33r.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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
}
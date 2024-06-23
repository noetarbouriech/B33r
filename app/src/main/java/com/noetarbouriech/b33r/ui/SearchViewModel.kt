package com.noetarbouriech.b33r.ui

import android.util.Log
import android.util.LogPrinter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noetarbouriech.b33r.network.ApiResponse
import com.noetarbouriech.b33r.network.MyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    // Expose screen UI state
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onSearchChange(search: String) {
        _uiState.update { currentState ->
            currentState.copy(searchText = search)
        }
        performSearch(search)
    }

    private fun performSearch(query: String) {
        viewModelScope.launch {
            try {
                val result: ApiResponse = MyApi.retrofitService.getBeers("name:$query*")
                _uiState.update { currentState ->
                    currentState.copy(results = result.hits)
                }
            } catch (e: Exception) {
                Log.e("SearchViewModel", "Search failed", e)
                _uiState.update { currentState ->
                    currentState.copy(results = emptyList())
                }
            }
        }
    }

    fun onToggleSearch() {
        _uiState.update { currentState ->
            currentState.copy(isSearching = !currentState.isSearching)
        }
    }
}

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
        Log.println(Log.ASSERT, "", search)
        viewModelScope.launch {
            val result: ApiResponse = MyApi.retrofitService.getBeers(search)
            _uiState.update { currentState ->
                currentState.copy(results = result.hits, searchText = search)
            }
        }
    }

    fun onToggleSearch() {
        onSearchChange("stout")
    }
}

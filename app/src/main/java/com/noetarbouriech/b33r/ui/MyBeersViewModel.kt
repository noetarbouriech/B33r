package com.noetarbouriech.b33r.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noetarbouriech.b33r.data.SavedBeerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MyBeersViewModel(val beerRepository: SavedBeerRepository): ViewModel() {
    // Expose screen UI state
    private val _uiState = MutableStateFlow(MyBeersUiState())
    val uiState: StateFlow<MyBeersUiState> = _uiState.asStateFlow()

    fun refreshData() {
        viewModelScope.launch {
            beerRepository.getAllTriedBeersStream()
                .combine(beerRepository.getAllPlanningBeersStream()) { triedBeers, planningBeers ->
                    triedBeers to planningBeers
                }
                .collect { (triedBeers, planningBeers) ->
                    Log.d("MainViewModel", "Beers tried: $triedBeers")
                    Log.d("MainViewModel", "Beers planning: $planningBeers")
                    _uiState.value = _uiState.value.copy(tried = triedBeers, planning = planningBeers)
                }
        }
    }


    init {
        refreshData()
    }
}

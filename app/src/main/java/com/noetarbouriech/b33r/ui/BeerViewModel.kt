package com.noetarbouriech.b33r.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noetarbouriech.b33r.data.SavedBeer
import com.noetarbouriech.b33r.data.SavedBeerRepository
import com.noetarbouriech.b33r.network.ApiResponse
import com.noetarbouriech.b33r.network.MyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BeerViewModel (savedStateHandle: SavedStateHandle, private val beerRepository: SavedBeerRepository) : ViewModel() {
    // Expose screen UI state
    private val beerId: String = checkNotNull(savedStateHandle["beerId"]) { "beerId is required" }
    private val _uiState = MutableStateFlow(BeerUiState())
    val uiState: StateFlow<BeerUiState> = _uiState.asStateFlow()


    private fun getData() {
        viewModelScope.launch {
            val result: ApiResponse = MyApi.retrofitService.getBeers("id:$beerId")
            _uiState.update { currentState ->
                currentState.copy(beer = result.hits.firstOrNull())
            }
        }
    }

    private fun getSavedBeer() {
        viewModelScope.launch {
            val savedBeer = beerRepository.getBeerStream(beerId).first()
            _uiState.value = _uiState.value.copy(savedBeer = savedBeer)
        }
    }

    fun setNull() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                beerRepository.deleteBeer(currentState.savedBeer!!)
                currentState.copy(savedBeer = null)
            }
        }
    }

    fun setPlanning() {
        viewModelScope.launch {
            val beer = uiState.value.beer ?: return@launch
            val newBeer = SavedBeer(
                id = beerId,
                name = beer.name,
                type = beer.style?.name ?: "",
                planning = true,
                score = null, // You might want to handle this based on UI interaction
                image = beer.labels!!.large
            )
            beerRepository.insertBeer(newBeer)
            _uiState.update { currentState ->
                currentState.copy(savedBeer = newBeer)
            }
        }
    }

    fun setTried(score: Int) {
        viewModelScope.launch {
            val beer = uiState.value.beer ?: return@launch
            val newBeer = SavedBeer(
                id = beerId,
                name = beer.name,
                type = beer.style?.name ?: "",
                planning = false,
                score = score,
                image = beer.labels!!.large
            )
            beerRepository.insertBeer(newBeer)
            _uiState.update { currentState ->
                currentState.copy(savedBeer = newBeer)
            }
        }
    }


    init {
        getData()
        getSavedBeer()
    }
}

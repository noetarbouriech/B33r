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
                currentState.copy(beer = result.hits[0])
            }
        }
    }

    private fun getSavedBeer() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(savedBeer = beerRepository.getBeerStream(beerId).first() )
            }
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
            val beer = SavedBeer(
                id = beerId,
                name = uiState.value.beer!!.name,
                type = uiState.value.beer!!.style!!.name,
                planning = true,
                score = null
            )
            beerRepository.insertBeer(beer)
            _uiState.update { currentState ->
                currentState.copy(savedBeer = beer)
            }
        }
    }

    fun setTried(score: Int) {
        viewModelScope.launch {
            val beer = SavedBeer(
                id = beerId,
                name = uiState.value.beer!!.name,
                type = uiState.value.beer!!.style!!.name,
                planning = false,
                score = score
            )
            beerRepository.insertBeer(beer)
            _uiState.update { currentState ->
                currentState.copy(savedBeer = beer)
            }
        }
    }

    init {
        getData()
        getSavedBeer()
    }

}

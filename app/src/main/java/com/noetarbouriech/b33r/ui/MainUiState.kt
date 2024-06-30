package com.noetarbouriech.b33r.ui

import com.noetarbouriech.b33r.data.SavedBeer
import com.noetarbouriech.b33r.network.Beer

data class MainUiState (
    val isSearching: Boolean = false,
    val searchText: String = "",
    val results: List<Beer>? = null,
    val favorites: List<SavedBeer?>? = null
)
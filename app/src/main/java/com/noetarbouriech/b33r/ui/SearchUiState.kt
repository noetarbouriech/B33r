package com.noetarbouriech.b33r.ui

import com.noetarbouriech.b33r.network.Beer

data class SearchUiState (
    val isSearching: Boolean = false,
    val searchText: String = "",
    val results: List<Beer>? = null
)

package com.noetarbouriech.b33r.ui

import com.noetarbouriech.b33r.data.SavedBeer

data class MyBeersUiState (
    val planning: List<SavedBeer?>? = null,
    val tried: List<SavedBeer?>? = null
)

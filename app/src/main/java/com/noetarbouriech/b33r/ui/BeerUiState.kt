package com.noetarbouriech.b33r.ui

import com.noetarbouriech.b33r.data.SavedBeer
import com.noetarbouriech.b33r.network.Beer

data class BeerUiState (
    val beer: Beer? = null,
    val savedBeer: SavedBeer? = null
)

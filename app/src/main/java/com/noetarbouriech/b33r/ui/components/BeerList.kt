package com.noetarbouriech.b33r.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.noetarbouriech.b33r.network.Beer

@Composable
fun BeerList(beers: List<Beer>) {
    LazyColumn {
        items(beers) { beer -> BeerItem(beer) }
    }
}

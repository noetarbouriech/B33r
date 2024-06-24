package com.noetarbouriech.b33r.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.noetarbouriech.b33r.network.Beer

@Composable
fun BeerList(beers: List<Beer>, navController: NavController) {
    LazyColumn {
        items(beers) { beer -> BeerItem(beer, navController = navController) }
    }
}

package com.noetarbouriech.b33r.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.noetarbouriech.b33r.network.Beer

@Composable
fun BeerItem(beer: Beer, score: Int? = null) {
    ListItem(
        headlineContent = { Text(beer.name) },
        overlineContent = { Text(beer.style?.category?.name!!) },
        trailingContent = { if (score != null) Text("$score⭐") },
        leadingContent = {
            Icon(
                Icons.Filled.Star,
                contentDescription = "test"
            )
        }
    )
    Divider()
}

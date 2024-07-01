package com.noetarbouriech.b33r.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.noetarbouriech.b33r.network.Beer

@Composable
fun BeerItem(
    id: String,
    name: String,
    style: String? = null,
    navController: NavController,
    score: Int? = null
) {

    ListItem(
        headlineContent = { Text(name) },
        overlineContent = {
            if (style != null) {
                Text(style)
            }
        },
        trailingContent = { if (score != null) Text("$score⭐") },
        leadingContent = {
            Icon(
                Icons.Filled.SportsBar,
                contentDescription = "test"
            )
        },
        modifier = Modifier.clickable {
            navController.navigate("beer/${id}")
        }
    )
    HorizontalDivider()
}

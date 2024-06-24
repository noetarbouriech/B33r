package com.noetarbouriech.b33r.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noetarbouriech.b33r.network.Beer

@Composable
fun BeerDescription(beer: Beer?, modifier: Modifier) {
    Column(
        modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text("\uD83C\uDF7A Brew style", style = MaterialTheme.typography.titleLarge)
        beer?.style?.name?.let { Text(it, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 2.dp)) }
        Spacer(Modifier.padding(12.dp))
        Text("\uD83C\uDF21\uFE0F ABV", style = MaterialTheme.typography.titleLarge)
        beer?.abv?.let { Text(it, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 2.dp)) }
        Spacer(Modifier.padding(12.dp))
        Text("\uD83D\uDE16 IBU", style = MaterialTheme.typography.titleLarge)
        beer?.ibu?.let { Text(it, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 2.dp)) }
        Spacer(Modifier.padding(12.dp))
        Text("\uD83D\uDCDD Description", style = MaterialTheme.typography.titleLarge)
        beer?.description?.let { Text(it, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 2.dp)) }
    }
}
package com.noetarbouriech.b33r.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.noetarbouriech.b33r.ui.MainViewModel

@Composable
fun ShareScreen(viewModel: MainViewModel = viewModel(), modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = Dp(22F))

        ){
            Text(
                text = "Your profile",
                style = MaterialTheme.typography.titleLarge
            )

        }
        Text(
            text = "Share",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

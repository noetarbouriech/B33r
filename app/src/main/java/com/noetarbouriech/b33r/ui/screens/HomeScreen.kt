package com.noetarbouriech.b33r.ui.screens

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.noetarbouriech.b33r.ui.MainViewModel
import com.noetarbouriech.b33r.ui.theme.AppTheme

@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel(), modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Welcome",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "Your favorites",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Last reviewed",
            style = MaterialTheme.typography.titleLarge
        )
        Button(
            onClick = { viewModel.getData() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "test api",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )
        }
        uiState.test?.name?.let { Text(
            text = it,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displayLarge
        ) }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}

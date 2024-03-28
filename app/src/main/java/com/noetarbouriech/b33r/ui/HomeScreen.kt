package com.noetarbouriech.b33r.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.noetarbouriech.b33r.ui.theme.B33rTheme

@Composable
fun MyScreen(viewModel: MainViewModel = viewModel(), modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState();

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { viewModel.incrementTest() }) {
            Text(text = uiState.test.toString(), modifier = modifier)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyScreenPreview() {
    B33rTheme {
        MyScreen()
    }
}

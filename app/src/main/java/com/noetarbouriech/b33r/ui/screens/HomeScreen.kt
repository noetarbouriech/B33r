package com.noetarbouriech.b33r.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.noetarbouriech.b33r.ui.MainViewModel
import com.noetarbouriech.b33r.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel(), modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "\uD83C\uDF7B Enjoy a pint !",
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = "Your favorites",
            style = MaterialTheme.typography.titleLarge
        )
        HorizontalMultiBrowseCarousel (
            state = rememberCarouselState { uiState.test.count() },
            modifier = Modifier.height(221.dp).fillMaxWidth(),
            itemSpacing = 8.dp,
            preferredItemWidth = 200.dp,
            contentPadding = PaddingValues(8.dp)
        ) { i ->
            val beer = uiState.test[i]
            Image(
                modifier = Modifier.height(281.dp).fillMaxWidth().maskClip(MaterialTheme.shapes.extraLarge),
                painter = rememberAsyncImagePainter(model = beer?.labels?.large),
                contentDescription = "test",
                contentScale = ContentScale.Crop
            )

        }
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
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}

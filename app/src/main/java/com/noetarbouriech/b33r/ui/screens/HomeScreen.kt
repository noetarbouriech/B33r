package com.noetarbouriech.b33r.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.noetarbouriech.b33r.ui.MainViewModel
import com.noetarbouriech.b33r.ui.components.BeerList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel(), navController: NavController, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel) {
        Log.println(Log.ASSERT,null ,uiState.favorites?.size.toString())
        viewModel.refreshData()
    }

    Text(
        text = "\uD83C\uDF7B Enjoy a pint !",
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier.padding(16.dp)
    )
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize().padding(top = if (uiState.isSearching) 0.dp else 24.dp)
    ) {
        SearchBar(
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            query = uiState.searchText,
            onQueryChange = viewModel::onSearchChange,
            onSearch = { viewModel.onSearchChange(uiState.searchText) },
            active = uiState.isSearching,
            onActiveChange = { viewModel.onToggleSearch() },
            modifier = Modifier.fillMaxWidth().padding(horizontal = if (uiState.isSearching) 0.dp else 8.dp)
        ) {
            if (uiState.results.isNullOrEmpty() || uiState.searchText.isEmpty()) {
                Text(
                    text = "No results \uD83D\uDE1E",
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                        .padding(top = 24.dp)
                )
            } else {
                BeerList(uiState.results!!, navController = navController)
            }
        }
        Text(
            text = "Your favorites",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(12.dp)
        )
        if (uiState.favorites != null && uiState.favorites!!.isNotEmpty()) {
            HorizontalMultiBrowseCarousel (
                state = rememberCarouselState { uiState.favorites!!.size },
                modifier = Modifier.height(221.dp).fillMaxWidth(),
                itemSpacing = 8.dp,
                preferredItemWidth = 200.dp,
                contentPadding = PaddingValues(8.dp)
            ) { i ->
                if (i < uiState.favorites!!.size) {
                    val beer = uiState.favorites!![i]
                    Box(
                        modifier = Modifier.clickable {
                            navController.navigate("beer/${beer?.id}")
                        }
                    ) {
                        Image(
                            modifier = Modifier.height(281.dp).fillMaxWidth()
                                .maskClip(MaterialTheme.shapes.extraLarge),
                            painter = rememberAsyncImagePainter(model = beer?.image),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            }
        } else {
            // Handle case where uiState.favorites is empty or null
            Text("No favorites to display")
        }
    }
}

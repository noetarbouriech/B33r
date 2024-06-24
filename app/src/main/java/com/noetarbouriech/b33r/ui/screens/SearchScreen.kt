package com.noetarbouriech.b33r.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.noetarbouriech.b33r.ui.SearchViewModel
import com.noetarbouriech.b33r.ui.components.BeerList

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(viewModel: SearchViewModel = viewModel(), navController: NavController, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
            SearchBar(
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                query = uiState.searchText,
                onQueryChange = viewModel::onSearchChange,
                onSearch = { viewModel.onSearchChange(uiState.searchText) },
                active = uiState.isSearching,
                onActiveChange = { viewModel.onToggleSearch() },
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
    }
}

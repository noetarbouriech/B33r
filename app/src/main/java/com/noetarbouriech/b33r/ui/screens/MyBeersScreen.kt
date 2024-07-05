package com.noetarbouriech.b33r.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.PlaylistRemove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.noetarbouriech.b33r.ui.MyBeersViewModel
import com.noetarbouriech.b33r.ui.components.BeerItem

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyBeersScreen(viewModel: MyBeersViewModel = viewModel(), navController: NavController, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    // Effect to refresh data on initial load
    LaunchedEffect(viewModel) {
        viewModel.refreshData()
    }

    var showTriedList by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text( if (showTriedList) {
                        "My tried beers"
                    } else {
                        "Planning to try"
                    }
                    )
                },
                actions = {
                    IconButton(onClick = {
                        showTriedList = !showTriedList; viewModel.refreshData()
                    }) {
                        Icon(
                            imageVector = if (showTriedList) Icons.Filled.PlaylistRemove else Icons.AutoMirrored.Filled.PlaylistAdd,
                            contentDescription = if (showTriedList) "Switch to Planning List" else "Switch to Tried List"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        if (showTriedList) {
            if (uiState.tried.isNullOrEmpty()) {
                // Display a message when there are no beers tried yet
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(
                        text = "Nothing tried yet...",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                // Display the list of tried beers in a LazyColumn
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(uiState.tried!!) { beer ->
                        BeerItem(
                            id = beer!!.id,
                            name = beer.name,
                            style = beer.type,
                            score = beer.score,
                            navController = navController
                        )
                    }
                }
            }
        } else {
            if (uiState.planning.isNullOrEmpty()) {
                // Display a message when there are no beers in planning list yet
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(
                        text = "Nothing planned yet...",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                // Display the list of planning beers in a LazyColumn
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(uiState.planning!!) { beer ->
                        BeerItem(
                            id = beer!!.id,
                            name = beer.name,
                            style = beer.type,
                            score = beer.score,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

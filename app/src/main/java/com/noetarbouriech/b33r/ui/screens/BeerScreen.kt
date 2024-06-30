package com.noetarbouriech.b33r.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.noetarbouriech.b33r.ui.BeerViewModel
import com.noetarbouriech.b33r.ui.components.BeerDescription
import com.noetarbouriech.b33r.ui.components.BeerSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerScreen(viewModel: BeerViewModel = viewModel(), beerId: String, navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()
    val showBottomSheet = remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = uiState.beer?.labels?.large),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                LargeTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black.copy(alpha = 0.6f),
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            uriHandler.openUri("https://www.google.com/search?q=${uiState.beer?.name}")
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                contentDescription = "Look up online"
                            )
                        }
                    },
                    title = {
                        uiState.beer?.let { Text(it.name) }
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton({ showBottomSheet.value = true }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.PlaylistAdd,
                    contentDescription = "Review"
                )
            }
        }
    ) { innerPadding ->
        BeerDescription(uiState.beer, Modifier.padding(innerPadding))
        BeerSheet(
            showBottomSheet,
            beer = uiState.savedBeer,
            setNull = { viewModel.setNull() },
            setPlanning = { viewModel.setPlanning() },
            setTried = { int -> viewModel.setTried(int) }
        )
    }
}

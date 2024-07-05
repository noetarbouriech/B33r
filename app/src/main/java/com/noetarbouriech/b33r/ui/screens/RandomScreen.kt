package com.noetarbouriech.b33r.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.noetarbouriech.b33r.ui.RandomBeerViewModel
import com.noetarbouriech.b33r.utils.ShakeDetector

@Composable
fun RandomScreen(modifier: Modifier = Modifier, navController: NavController, viewModel: RandomBeerViewModel = viewModel()) {
    val randomBeerId by viewModel.randomBeerId.collectAsState(null)

    val context = LocalContext.current

    var shakeDetector by remember { mutableStateOf(ShakeDetector(context)) }

    DisposableEffect(key1 = context) {
        shakeDetector = ShakeDetector(context)
        shakeDetector.setOnShakeListener {
            viewModel.fetchRandomBeerId()
        }

        onDispose {
            shakeDetector.stop()
        }
    }

    // Navigate to random beer screen when randomBeerId is fetched
    if (randomBeerId != null) {
        LaunchedEffect(randomBeerId) {
            navController.navigate("beer/$randomBeerId")
            viewModel.resetRandomBeerId() // Reset the randomBeerId after navigation to prevent multiple navigations
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(),
            modifier = Modifier.fillMaxWidth().padding(18.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp).fillMaxWidth()
            ) {
                Text(
                    text = "Shake to find a beer !",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp).align(Alignment.CenterHorizontally)
                )
                Icon(Icons.Filled.Casino, "", modifier = Modifier.size(64.dp).align(Alignment.CenterHorizontally))
            }
        }
    }
}

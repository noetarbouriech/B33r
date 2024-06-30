package com.noetarbouriech.b33r

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.noetarbouriech.b33r.data.OfflineBeersRepository
import com.noetarbouriech.b33r.data.SavedBeerDatabase
import com.noetarbouriech.b33r.data.SavedBeerRepository
import com.noetarbouriech.b33r.ui.BeerViewModel
import com.noetarbouriech.b33r.ui.screens.HomeScreen
import com.noetarbouriech.b33r.ui.components.NavBar
import com.noetarbouriech.b33r.ui.components.NavItem
import com.noetarbouriech.b33r.ui.screens.BeerScreen
import com.noetarbouriech.b33r.ui.screens.SearchScreen
import com.noetarbouriech.b33r.ui.screens.ShareScreen
import com.noetarbouriech.b33r.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    private lateinit var repository: SavedBeerRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get an instance of SavedBeerDatabase
        val database = SavedBeerDatabase.getDatabase(this)

        // Get an instance of SavedBeerDao from the database
        val beerDao = database.savedBeerDao()
        // Initialize the repository with the SavedBeerDao
        repository = OfflineBeersRepository(beerDao)

        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val navItems = listOf(
                    NavItem("Home", Icons.Filled.Home, "Home"),
                    NavItem("Search", Icons.Filled.Search, "Search"),
                    NavItem("Share", Icons.Filled.Share, "Share")
                )
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { NavBar(navItems, navController) }
                ) { innerPadding ->
                    NavHost (
                        navController = navController,
                        startDestination = "Home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("Home") { HomeScreen() }
                        composable("Search") { SearchScreen(navController = navController) }
                        composable("Share") { ShareScreen() }
                        composable(
                            "beer/{beerId}",
                            arguments = listOf(
                                navArgument("beerId") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val beerId = backStackEntry.arguments?.getString("beerId") ?: throw IllegalStateException("beerId cannot be null")
                            // Initialize BeerViewModel using the savedStateHandle
                            val viewModel: BeerViewModel = viewModel(
                                key = "BeerViewModel_$beerId",
                                initializer = {
                                    val savedStateHandle = backStackEntry.savedStateHandle
                                    savedStateHandle["beerId"] = beerId
                                    BeerViewModel(savedStateHandle = backStackEntry.savedStateHandle, beerRepository = repository)
                                }
                            )
                            BeerScreen(
                                viewModel = viewModel,
                                beerId = beerId,
                                navController = navController
                                )
                        }

                    }
                }
            }
        }
    }
}
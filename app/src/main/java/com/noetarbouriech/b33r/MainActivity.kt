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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.noetarbouriech.b33r.ui.screens.HomeScreen
import com.noetarbouriech.b33r.ui.components.NavBar
import com.noetarbouriech.b33r.ui.components.NavItem
import com.noetarbouriech.b33r.ui.screens.SearchScreen
import com.noetarbouriech.b33r.ui.screens.ShareScreen
import com.noetarbouriech.b33r.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                        composable("Search") { SearchScreen() }
                        composable("Share") { ShareScreen() }
                    }
                }
            }
        }
    }
}
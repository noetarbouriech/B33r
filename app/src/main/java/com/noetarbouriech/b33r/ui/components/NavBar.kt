package com.noetarbouriech.b33r.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.noetarbouriech.b33r.ui.theme.AppTheme

data class NavItem(val label: String, val icon: ImageVector, val route: String)

@Composable
fun NavBar(navItems: List<NavItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedItem = navItems.indexOfFirst { it.route == currentRoute }

    NavigationBar (
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = {
                    if (selectedItem != index) {
                        navController.navigate(item.route) {
                            // Pop up to the start destination to avoid building up a large back stack
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NavBarPreview() {
    AppTheme {
        val navController = rememberNavController()
        val navItems = listOf(
            NavItem("Home", Icons.Filled.Home, "Home"),
            NavItem("Search", Icons.Filled.Search, "Search"),
            NavItem("Share", Icons.Filled.Share, "Share")
        )
        NavBar(navItems, navController)
    }
}

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.noetarbouriech.b33r.ui.theme.AppTheme

data class NavItem(val label: String, val icon: ImageVector, val route: String)

@Composable
fun NavBar(navItems: List<NavItem>, navController: NavHostController) {
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar (
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary,
    ){
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = {
                    if (selectedItem != index) {
                        selectedItem = index
                        navController.navigate(item.label)
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

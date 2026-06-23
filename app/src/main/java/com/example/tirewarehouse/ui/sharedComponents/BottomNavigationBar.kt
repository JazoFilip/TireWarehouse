package com.example.tirewarehouse.ui.sharedComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tirewarehouse.R
import com.example.tirewarehouse.ui.theme.Yellow100

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    NavigationBar(
        containerColor = Yellow100
    ) {
        val currentRoute = navController
            .currentBackStackEntryAsState()
            .value?.destination?.route

        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = currentRoute?.startsWith("inventory") == true,
            onClick = {
                navController.navigate("inventory") {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.inventory_icon),
                    contentDescription = "Inventory",
                    tint = if (currentRoute?.startsWith("inventory") == true)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            label = { Text("Inventory") }
        )

        NavigationBarItem(
            selected = currentRoute == "addTire",
            onClick = {
                navController.navigate("addTire") {
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.Add, contentDescription = "Add Tires") },
            label = { Text("Add Tires") }
        )
    }
}
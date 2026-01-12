package com.example.tirewarehouse.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import com.example.tirewarehouse.ui.components.BottomNavigationBar
import com.example.tirewarehouse.ui.homeScreen.HomeScreen
import com.example.tirewarehouse.ui.components.TopBar
import com.example.tirewarehouse.ui.inventoryScreen.InventoryScreen
import com.example.tirewarehouse.viewModel.TireViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tirewarehouse.ui.theme.Yellow100

@Composable
fun AppNavigation(
    viewModel: TireViewModel
){
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar() },
        bottomBar =  { BottomNavigationBar(navController) },
        containerColor = Yellow100
    ){ padding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ){
            composable("home"){
                HomeScreen(viewModel, navController)
            }
            composable(
                "inventory?type={type}",
                arguments = listOf(
                    navArgument("type") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) { backStackEntry ->
                val type = backStackEntry.arguments?.getString("type")
                InventoryScreen(viewModel = viewModel, tireType = type)
            }
        }
    }
}
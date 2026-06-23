package com.example.tirewarehouse.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tirewarehouse.presentation.addTireScreenViewModel.AddTireViewModel
import com.example.tirewarehouse.presentation.homeScreenViewModel.HomeViewModel
import com.example.tirewarehouse.presentation.inventoryScreenViewModel.InventoryViewModel
import com.example.tirewarehouse.ui.addTireScreen.AddTireScreen
import com.example.tirewarehouse.ui.homeScreen.HomeScreen
import com.example.tirewarehouse.ui.inventoryScreen.InventoryScreen
import com.example.tirewarehouse.ui.sharedComponents.BottomNavigationBar
import com.example.tirewarehouse.ui.sharedComponents.TopBar
import com.example.tirewarehouse.ui.theme.Yellow100

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController = navController) },
        containerColor = Yellow100
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
                val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

                HomeScreen(
                    uiState = uiState,
                    onTireTypeClick = { type ->
                        navController.navigate("inventory?type=$type") {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(
                route = "inventory?type={type}",
                arguments = listOf(
                    navArgument("type") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) { backStackEntry ->
                val type = backStackEntry.arguments?.getString("type")

                val inventoryViewModel: InventoryViewModel = viewModel(
                    factory = InventoryViewModel.provideFactory(type),
                    key = type ?: "all"
                )
                val uiState by inventoryViewModel.uiState.collectAsStateWithLifecycle()

                InventoryScreen(
                    uiState = uiState,
                    onWidthChange = { inventoryViewModel.updateWidth(it) },
                    onHeightChange = { inventoryViewModel.updateHeight(it) },
                    onDiameterChange = { inventoryViewModel.updateDiameter(it) },
                    onApplyQuantity = { id, delta -> inventoryViewModel.updateQuantity(id, delta) }
                )
            }

            composable("addTire") {
                val addTireViewModel: AddTireViewModel = viewModel(factory = AddTireViewModel.Factory)
                val uiState by addTireViewModel.uiState.collectAsStateWithLifecycle()

                AddTireScreen(
                    uiState = uiState,
                    onWidthChange = { addTireViewModel.setWidth(it) },
                    onHeightChange = { addTireViewModel.setHeight(it) },
                    onDiameterChange = { addTireViewModel.setDiameter(it) },
                    onTypeChange = { addTireViewModel.setType(it) },
                    onSeasonChange = { addTireViewModel.setSeason(it) },
                    onBrandChange = { addTireViewModel.setBrand(it) },
                    onQuantityChange = { addTireViewModel.setQuantity(it) },
                    onAddClick = { addTireViewModel.addOrIncreaseTire() },
                )
            }
        }
    }
}
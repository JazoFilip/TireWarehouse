package com.example.tirewarehouse.ui.inventoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tirewarehouse.presentation.inventoryScreenViewModel.InventoryUiState
import com.example.tirewarehouse.ui.inventoryScreen.components.TireCard
import com.example.tirewarehouse.ui.sharedComponents.DimensionInput

@Composable
fun InventoryScreen(
    uiState: InventoryUiState,
    onWidthChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    onDiameterChange: (String) -> Unit,
    onApplyQuantity: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        modifier = modifier.fillMaxSize()
    ) {
        when (uiState) {
            is InventoryUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is InventoryUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = uiState.message, color = Color.Red)
                }
            }
            is InventoryUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        DimensionInput(
                            label = "Width",
                            value = uiState.searchQueryWidth,
                            onValueChange = onWidthChange,
                            modifier = Modifier.weight(1f)
                        )
                        DimensionInput(
                            label = "Height",
                            value = uiState.searchQueryHeight,
                            onValueChange = onHeightChange,
                            modifier = Modifier.weight(1f)
                        )
                        DimensionInput(
                            label = "Diameter",
                            value = uiState.searchQueryDiameter,
                            onValueChange = onDiameterChange,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(
                            items = uiState.tires,
                            key = { it.tireId }
                        ) { tireEntity ->
                            TireCard(
                                tire = tireEntity,
                                onApplyQuantity = { delta ->
                                    onApplyQuantity(tireEntity.tireId, delta)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
package com.example.tirewarehouse.ui.addTireScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tirewarehouse.data.enums.Brand
import com.example.tirewarehouse.data.enums.Season
import com.example.tirewarehouse.data.enums.TireType
import com.example.tirewarehouse.presentation.addTireScreenViewModel.AddTireUiState
import com.example.tirewarehouse.ui.addTireScreen.components.EnumDropdown
import com.example.tirewarehouse.ui.addTireScreen.components.QuantityInput
import com.example.tirewarehouse.ui.sharedComponents.DimensionInput
import com.example.tirewarehouse.ui.theme.Purple40

@Composable
fun AddTireScreen(
    uiState: AddTireUiState,
    onWidthChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    onDiameterChange: (String) -> Unit,
    onTypeChange: (TireType) -> Unit,
    onSeasonChange: (Season) -> Unit,
    onBrandChange: (Brand) -> Unit,
    onQuantityChange: (Int?) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        modifier = modifier
    ) {
        when (uiState) {
            is AddTireUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is AddTireUiState.Failure -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = uiState.message, color = Color.Red)
                }
            }
            is AddTireUiState.Form -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        DimensionInput(
                            label = "Width",
                            value = uiState.width,
                            onValueChange = onWidthChange,
                            modifier = Modifier.weight(1f)
                        )
                        DimensionInput(
                            label = "Height",
                            value = uiState.height,
                            onValueChange = onHeightChange,
                            modifier = Modifier.weight(1f)
                        )
                        DimensionInput(
                            label = "Diameter",
                            value = uiState.diameter,
                            onValueChange = onDiameterChange,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        EnumDropdown(
                            label = "Type",
                            values = TireType.values(),
                            selected = uiState.type,
                            onSelected = onTypeChange,
                            modifier = Modifier.weight(1f)
                        )
                        EnumDropdown(
                            label = "Season",
                            values = Season.values(),
                            selected = uiState.season,
                            onSelected = onSeasonChange,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        EnumDropdown(
                            label = "Brand",
                            values = Brand.values(),
                            selected = uiState.brand,
                            onSelected = onBrandChange,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    QuantityInput(
                        label = "Quantity",
                        value = uiState.quantity,
                        onValueChange = onQuantityChange,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = onAddClick,
                            colors = ButtonDefaults.buttonColors(containerColor = Purple40)
                        ) {
                            Text("Add Tire")
                        }
                    }
                }
            }
        }
    }
}
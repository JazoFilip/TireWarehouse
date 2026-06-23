package com.example.tirewarehouse.ui.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tirewarehouse.R
import com.example.tirewarehouse.data.enums.TireType
import com.example.tirewarehouse.presentation.homeScreenViewModel.HomeUiState
import com.example.tirewarehouse.ui.homeScreen.components.InventoryCard
import com.example.tirewarehouse.ui.homeScreen.components.SummaryCard
import com.example.tirewarehouse.ui.theme.Clear
import com.example.tirewarehouse.ui.theme.SkyLightBlue
import com.example.tirewarehouse.ui.theme.Yellow50

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onTireTypeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        modifier = modifier.fillMaxSize()
    ) {
        when (uiState) {
            is HomeUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is HomeUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = uiState.message, color = Color.Red)
                }
            }
            is HomeUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SummaryCard(
                        image = R.drawable.wheel,
                        title = uiState.totalTires.toString(),
                        subTitle = "Total in stock"
                    )

                    Text("Inventory", fontWeight = FontWeight.Bold, fontSize = 20.sp)

                    InventoryCard(
                        color = SkyLightBlue,
                        image = R.drawable.car,
                        title = "No. of car tires",
                        subtitle = uiState.totalCarTires.toString(),
                        onClick = { onTireTypeClick(TireType.CAR.name) }
                    )

                    InventoryCard(
                        color = Yellow50,
                        image = R.drawable.tractor,
                        title = "No. of tractor tires",
                        subtitle = uiState.totalTractorTires.toString(),
                        onClick = { onTireTypeClick(TireType.TRACTOR.name) }
                    )

                    InventoryCard(
                        color = Clear,
                        image = R.drawable.truck,
                        title = "No. of truck tires",
                        subtitle = uiState.totalTruckTires.toString(),
                        onClick = { onTireTypeClick(TireType.TRUCK.name) }
                    )
                }
            }
        }
    }
}
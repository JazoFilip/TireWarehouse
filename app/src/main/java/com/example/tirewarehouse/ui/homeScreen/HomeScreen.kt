package com.example.tirewarehouse.ui.homeScreen

import InventoryCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tirewarehouse.R
import com.example.tirewarehouse.ui.theme.Clear
import com.example.tirewarehouse.ui.theme.SkyLightBlue
import com.example.tirewarehouse.ui.theme.Yellow100
import com.example.tirewarehouse.ui.theme.Yellow50
import com.example.tirewarehouse.viewModel.TireViewModel
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun HomeScreen(
    viewModel: TireViewModel
){
    val totalTires by viewModel.totalTires.observeAsState(0)
    val totalCarTires by viewModel.totalCarTires.observeAsState(0)
    val totalTractorTires by viewModel.totalTractorTires.observeAsState(0)
    val totalTruckTires by viewModel.totalTruckTires.observeAsState(0)

    Scaffold(
        containerColor = Yellow100,
        topBar = {TopBar()},
        bottomBar = {BottomNavigationBar()}

    ) { padding ->
        Surface(
            modifier = Modifier.padding(padding),
            shape = RoundedCornerShape(24.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SummaryCard(R.drawable.wheel, totalTires.toString(), "Total in stock")
                Text("Inventory", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                InventoryCard(SkyLightBlue,R.drawable.car, "No. of car tires", totalCarTires.toString())
                InventoryCard(Yellow50,R.drawable.tractor, "No. of tractor tires", totalTractorTires.toString())
                InventoryCard(Clear,R.drawable.truck, "No. of truck tires", totalTruckTires.toString())
            }
        }
    }
}
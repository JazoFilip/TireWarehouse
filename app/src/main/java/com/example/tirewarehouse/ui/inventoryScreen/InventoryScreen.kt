package com.example.tirewarehouse.ui.inventoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tirewarehouse.ui.homeScreen.BottomNavigationBar
import com.example.tirewarehouse.ui.homeScreen.TopBar
import com.example.tirewarehouse.ui.theme.Yellow100

@Preview
@Composable
fun InventoryScreen(

){
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
                TireCard()
                TireCard()
            }
        }
    }
}
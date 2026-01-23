package com.example.tirewarehouse.ui.inventoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tirewarehouse.viewModel.TireViewModel
import androidx.compose.foundation.lazy.items
import com.example.tirewarehouse.ui.components.DimensionInput


@Composable
fun InventoryScreen(
        viewModel: TireViewModel,
        tireType: String? = null
){
    val tires by viewModel.getFilteredTires(tireType).observeAsState(emptyList())
    val selectedWidth by viewModel.Width.observeAsState()
    val selectedHeight by viewModel.Height.observeAsState()
    val selectedDiameter by viewModel.Diameter.observeAsState()



    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                DimensionInput(
                    label = "Width",
                    value = selectedWidth,
                    onValueChange = { viewModel.setWidth(it) },
                    modifier = Modifier.weight(1f)
                )
                DimensionInput(
                    label = "Height",
                    value = selectedHeight,
                    onValueChange = { viewModel.setHeight(it) },
                    modifier = Modifier.weight(1f)
                )
                DimensionInput(
                    label = "Diameter",
                    value = selectedDiameter,
                    onValueChange = { viewModel.setDiameter(it) },
                    modifier = Modifier.weight(1f)
                )
            }


            LazyColumn(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = tires,
                    key = { it.id }
                ) { tire ->
                    TireCard(
                        tire,
                        onApplyQuantity = {id,delta ->
                            viewModel.updateQuantity(id,delta)
                        }
                    )
                }
            }
        }
    }
}

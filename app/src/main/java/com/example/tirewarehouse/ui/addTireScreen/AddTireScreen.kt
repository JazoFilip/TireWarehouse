package com.example.tirewarehouse.ui.addTireScreen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tirewarehouse.data.enums.Brand
import com.example.tirewarehouse.data.enums.Location
import com.example.tirewarehouse.data.enums.Season
import com.example.tirewarehouse.data.enums.TireType
import com.example.tirewarehouse.ui.components.DimensionInput
import com.example.tirewarehouse.ui.theme.Purple40
import com.example.tirewarehouse.viewModel.TireViewModel

@Composable
fun  AddTireScreen(
    viewModel: TireViewModel
){
    val selectedWidth by viewModel.Width.observeAsState()
    val selectedHeight by viewModel.Height.observeAsState()
    val selectedDiameter by viewModel.Diameter.observeAsState()
    val selectedType by viewModel.Type.observeAsState()
    val selectedBrand by viewModel.Brand.observeAsState()
    val selectedSeason by viewModel.Season.observeAsState()
    val selectedLocation by viewModel.Location.observeAsState()
    val selectedQuantity by viewModel.Quantity.observeAsState()

    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DimensionInput(
                    "Width",
                    value = selectedWidth,
                    onValueChange = { viewModel.setWidth(it) },
                    modifier = Modifier.weight(1f)
                )
                DimensionInput(
                    "Height",
                    value = selectedHeight,
                    onValueChange = { viewModel.setHeight(it) },
                    modifier = Modifier.weight(1f)
                )
                DimensionInput(
                    "Diameter",
                    value = selectedDiameter,
                    onValueChange = { viewModel.setDiameter(it) },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                EnumDropdown(
                    label = "Type",
                    values = TireType.values(),
                    selected = selectedType,
                    onSelected = { viewModel.setType(it) },
                    modifier = Modifier.weight(1f)
                )
                EnumDropdown(
                    label = "Season",
                    values = Season.values(),
                    selected = selectedSeason,
                    onSelected = { viewModel.setSeason(it) },
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
                    selected = selectedBrand,
                    onSelected = { viewModel.setBrand(it) },
                    modifier = Modifier.weight(1f)
                )
                EnumDropdown(
                    label = "Location",
                    values = Location.values(),
                    selected = selectedLocation,
                    onSelected = { viewModel.setLocation(it) },
                    modifier = Modifier.weight(1f)
                )
            }
            QuantityInput(
                "Quantity",
                value = selectedQuantity,
                onValueChange = { viewModel.setQuantity(it) },
            )
            Spacer(Modifier.height(15.dp))
            Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {viewModel.addOrIncreaseTire()},
                colors = ButtonDefaults.buttonColors(containerColor = Purple40)
            ) {
                Text("Add Tire")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {viewModel.removeTire()},
                colors = ButtonDefaults.buttonColors(containerColor = Purple40)
            ) {
                Text("Remove Tire")
            }
        }
        }
    }
}
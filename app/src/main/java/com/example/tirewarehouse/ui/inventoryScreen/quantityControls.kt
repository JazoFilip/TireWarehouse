package com.example.tirewarehouse.ui.inventoryScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuantityControls(
    onClick: (Int) -> Unit
) {
    var selectedQuantity by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "−",
                fontSize = 32.sp,
                modifier = Modifier
                    .clickable { selectedQuantity-- }
                    .padding(8.dp)
            )

            Text(
                text = selectedQuantity.toString(),
                fontSize = 22.sp
            )

            Text(
                text = "+",
                fontSize = 32.sp,
                modifier = Modifier
                    .clickable { selectedQuantity++ }
                    .padding(8.dp)
            )
        }

        androidx.compose.material3.Button(
            onClick = {
                onClick(selectedQuantity)
                selectedQuantity = 0
            },
            enabled = selectedQuantity != 0
        ) {
            Text("Apply")
        }
    }
}
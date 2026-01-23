package com.example.tirewarehouse.ui.inventoryScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tirewarehouse.ui.theme.Green40
import com.example.tirewarehouse.ui.theme.Purple40
import com.example.tirewarehouse.ui.theme.Purple80
import com.example.tirewarehouse.ui.theme.Red40

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

            Button(
                onClick = { selectedQuantity-- },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Red40),

            ) {
                Text(
                    text = "−",
                    fontSize = 26.sp,
                )
            }

            Text(
                text = selectedQuantity.toString(),
                fontSize = 22.sp
            )

            Button(
                onClick = { selectedQuantity++ },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Green40)
            ) {
                Text(
                    text = "+",
                    fontSize = 26.sp,
                )
            }
        }

        Button(
            onClick = {
                onClick(selectedQuantity)
                selectedQuantity = 0
            },
            enabled = selectedQuantity != 0,
            colors = ButtonDefaults.buttonColors(containerColor = Purple40)
        ) {
            Text("Apply")
        }
    }
}
package com.example.tirewarehouse.ui.inventoryScreen



import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DimensionInput(
        label: String = "width",
        onValueChange: (Float?) -> Unit,
        modifier: Modifier = Modifier
){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        value = text,
        onValueChange = {
            text = it
            onValueChange(it.toFloatOrNull())
        },
        label = { Text (label) },
        singleLine = true,
        modifier = modifier
    )
}
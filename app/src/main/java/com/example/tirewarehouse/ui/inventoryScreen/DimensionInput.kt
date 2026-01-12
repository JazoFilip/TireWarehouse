package com.example.tirewarehouse.ui.inventoryScreen



import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DimensionInput(
        label: String = "width",
        value: Float? = 0f,
        onValueChange: (Float?) -> Unit,
        modifier: Modifier = Modifier
){
    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        value = value?.clean() ?: "",
        onValueChange = {
            onValueChange(it.toFloatOrNull())
        },
        label = { Text (label) },
        singleLine = true,
        modifier = modifier
    )
}
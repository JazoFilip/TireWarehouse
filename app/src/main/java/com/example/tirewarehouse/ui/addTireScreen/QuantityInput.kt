package com.example.tirewarehouse.ui.addTireScreen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun QuantityInput(
    label: String = "quantity",
    value: Int?,
    onValueChange: (Int?) -> Unit,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        value = value?.toString() ?: "",
        onValueChange = {
            onValueChange(it.toIntOrNull())
        },
        label = { Text (label) },
        singleLine = true,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
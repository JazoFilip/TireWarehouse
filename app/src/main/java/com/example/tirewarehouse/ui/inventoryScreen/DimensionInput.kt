package com.example.tirewarehouse.ui.inventoryScreen



import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun DimensionInput(
    label: String = "width",
    value: String? = "",
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        value = value ?: "",
        onValueChange = { newValue ->
            // Allow only digits and a single decimal point
            if (newValue.matches(Regex("""\d*\.?\d*"""))) {
                onValueChange(newValue)
            }
        },
        label = { Text (label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        ),
        modifier = modifier
    )
}
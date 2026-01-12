package com.example.tirewarehouse.ui.addTireScreen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Enum<T>> EnumDropdown(
    values: Array<T>,
    selected: T?,
    onSelected: (T) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var query by remember(selected) { mutableStateOf(selected?.name ?: "") }

    val filteredItems = values.filter {
        it.name.contains(query, ignoreCase = true)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it
        },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                expanded = true
            },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable),
            singleLine = true
        )

        ExposedDropdownMenu(
            expanded = expanded && filteredItems.isNotEmpty(),
            onDismissRequest = { expanded = false }
        ) {
            filteredItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item.name) },
                    onClick = {
                        query = item.name
                        onSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}
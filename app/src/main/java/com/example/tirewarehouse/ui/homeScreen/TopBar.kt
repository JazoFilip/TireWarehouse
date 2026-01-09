package com.example.tirewarehouse.ui.homeScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.tirewarehouse.ui.theme.Yellow100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  TopBar(

) {
    TopAppBar(
        title = {
            Text(
                text = "Tire Warehouse",
                fontWeight = FontWeight.Bold
                )

        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Localized description"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Yellow100)
    )
}
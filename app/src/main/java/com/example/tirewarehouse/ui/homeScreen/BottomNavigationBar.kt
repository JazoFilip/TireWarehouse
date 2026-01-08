package com.example.tirewarehouse.ui.homeScreen



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tirewarehouse.R
import com.example.tirewarehouse.ui.theme.Yellow100


@Composable
fun BottomNavigationBar(){
    NavigationBar(
        containerColor = Yellow100
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Default.Home,"home")},
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                 ) {
                    Image(
                        painter = painterResource(R.drawable.tire),
                        contentDescription = "Inventory",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            },
            label = { Text("Inventory")}
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {Icon(Icons.Default.Add,"AddTires")},
            label = {Text("AddTires")}
        )
    }
}
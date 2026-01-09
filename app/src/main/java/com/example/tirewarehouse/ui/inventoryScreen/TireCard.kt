package com.example.tirewarehouse.ui.inventoryScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tirewarehouse.R
import com.example.tirewarehouse.ui.theme.SkyLightBlue

@Preview
@Composable
fun TireCard(

){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SkyLightBlue)
    ){
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(

                ) {
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

                    Text("255/30R20", fontSize = 20.sp)
                }

                Row(

                ) {

                    Text("Michelin", fontSize = 20.sp)

                    Box(
                        modifier = Modifier.size(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.sun),
                            contentDescription = "sun",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text("12", fontSize = 20.sp)
        }
    }
}
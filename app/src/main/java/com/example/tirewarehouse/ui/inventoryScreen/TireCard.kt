package com.example.tirewarehouse.ui.inventoryScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tirewarehouse.R
import com.example.tirewarehouse.data.Tire
import com.example.tirewarehouse.data.enums.Season
import com.example.tirewarehouse.data.enums.TireType
import com.example.tirewarehouse.ui.theme.SkyLightBlue

@Composable
fun TireCard(
    tire: Tire,
    onApplyQuantity: (String, Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable {expanded = !expanded},
        colors = CardDefaults.cardColors(containerColor = SkyLightBlue)
    ) {



        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier.size(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(TireType.fromString(tire.type).imageRes),
                            contentDescription = "Inventory",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Text(PrintTireDimensions(tire.width,tire.height,tire.diameter), fontSize = 20.sp)
                    PrintTireDimensions(tire.width,tire.height,tire.diameter)
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(tire.brand, fontSize = 20.sp)

                    Box(
                        modifier = Modifier.size(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Season.FromString(tire.season).imageRes),
                            contentDescription = "season",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                }
                Text(tire.location, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(tire.quantity.toString(), fontSize = 20.sp)
        }
        if (expanded) {
            QuantityControls { delta ->
                onApplyQuantity(tire.id, delta)
            }
        }
    }
}
    fun Float.clean(): String {
        if (this % 1.0 == 0.0) {
            return this.toInt().toString()
        }
        return this.toString()
    }

fun PrintTireDimensions(width: Float, height: Float, diameter: Float) : String{
    if(height == 0f){
        return "${width.clean()} - ${diameter.clean()}"
    }
    return "${width.clean()}/${height.clean()}R${diameter.clean()}"
}



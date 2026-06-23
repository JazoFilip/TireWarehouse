package com.example.tirewarehouse.ui.inventoryScreen.components

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
import com.example.tirewarehouse.data.database.TireEntity
import com.example.tirewarehouse.data.enums.Season
import com.example.tirewarehouse.data.enums.TireType
import com.example.tirewarehouse.ui.theme.SkyLightBlue

@Composable
fun TireCard(
    tire: TireEntity,
    onApplyQuantity: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable { expanded = !expanded },
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
                    val tireType = TireType.fromString(tire.type.orEmpty())
                    Box(
                        modifier = Modifier.size(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(tireType.imageRes),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Text(
                        text = formatTireDimensions(tire.width, tire.height, tire.diameter),
                        fontSize = 20.sp
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(tire.brand.orEmpty(), fontSize = 20.sp)

                    val season = Season.FromString(tire.season.orEmpty())
                    Box(
                        modifier = Modifier.size(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(season.imageRes),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(tire.quantity.toString(), fontSize = 20.sp)
        }

        if (expanded) {
            QuantityControls(availableQuantity = tire.quantity) { delta ->
                onApplyQuantity(delta)
                expanded = false
            }
        }
    }
}

fun formatTireDimensions(width: String?, height: String?, diameter: String?): String {
    if (width.isNullOrEmpty() || diameter.isNullOrEmpty()) return "Unknown size"
    if (height.isNullOrEmpty() || height == "0") {
        return "$width - $diameter"
    }
    return "$width/${height}R$diameter"
}
package com.example.tirewarehouse.ui.homeScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.unit.dp
import com.example.tirewarehouse.ui.theme.Yellow50

@Composable
fun SummaryCard(image: Int,title: String,subTitle: String,){
    Card(
        colors = CardDefaults.cardColors(containerColor = Yellow50)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Image(
                painter = painterResource(image),
                contentDescription = "inventorySummary",
                Modifier.size(40.dp)
            )

            Text(title)
            Text(subTitle)
        }
    }
}

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tirewarehouse.R
import com.example.tirewarehouse.ui.theme.SkyLightBlue

@Composable
fun InventoryCard(
    color: Color = SkyLightBlue,
    image: Int = R.drawable.car,
    title: String = "No. of tires",
    subtitle: String = "500",
    onClick: () -> Unit

){
    Card(
       colors = CardDefaults.cardColors(containerColor = color),
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(

        ){
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(title, fontWeight = FontWeight.Bold)
                Text(subtitle, fontWeight = FontWeight.Bold, fontSize = 20.sp)
               Button(
                    onClick = onClick,
                    enabled = true,
                   colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("View Inventory", color = Color.Black)
                    Icon(Icons.AutoMirrored.Filled.ArrowForward,"null", tint = Color.Black)
                }
            }
                Box(

                    modifier = Modifier.fillMaxWidth()
                        .height(100.dp)
                ) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = "image1",
                        modifier = Modifier.fillMaxSize()
                    )
                }
        }
    }
}
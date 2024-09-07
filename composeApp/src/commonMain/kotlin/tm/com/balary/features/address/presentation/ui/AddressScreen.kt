package tm.com.balary.features.address.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import tm.com.balary.features.auth.presentation.ui.BackScreen

class AddressScreen : Screen {
    @Composable
    override fun Content() {
        Address(Modifier.fillMaxSize())
    }
}

@Composable
fun Address(modifier: Modifier = Modifier) {
    BackScreen(modifier, title = "Salgylarym") {
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(20) {
                AddressItem(Modifier.fillMaxWidth())
            }
            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {

                    }
                ) {
                    Icon(
                        Icons.Default.AddBox,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "add",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Salgy goşmak", style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700
                    ), color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}
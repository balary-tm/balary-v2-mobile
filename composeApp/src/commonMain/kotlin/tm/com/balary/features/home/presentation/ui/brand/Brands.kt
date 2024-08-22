package tm.com.balary.features.home.presentation.ui.brand

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tm.com.balary.features.home.presentation.ui.product.HomeProductSection

@Composable
fun Brands(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(20.dp)
        ).padding(vertical = 16.dp)
    ) {
        Spacer(Modifier.height(12.dp))
        HomeProductSection(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), "Brendlar")
        Spacer(Modifier.height(12.dp))
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = 16.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(20) {
                BrandItem(
                    image = ""
                )
            }
        }
    }
}
package tm.com.balary.features.home.presentation.ui.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import tm.com.balary.features.ads.presentation.ui.AdsComponent
import tm.com.balary.features.category.presentation.ui.subcategory.SubCategory
import tm.com.balary.features.category.presentation.ui.subcategory.SubCategoryScreen
import tm.com.balary.features.product.presentation.ui.ProductCard

@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    title: String,
    showBanner: Boolean = false,
    adsCount: Int = 0
) {
    val navigator = LocalNavigator.currentOrThrow
    Column(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(20.dp)
        ).padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                horizontal = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(adsCount) {
                AdsComponent(
                    modifier = Modifier.weight(1f).height(140.dp),
                    image = ""
                )
            }
        }
        Spacer(Modifier.height(12.dp))
        HomeProductSection(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), title, onClick = {
                navigator.push(SubCategoryScreen())
            })
        Spacer(Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(20) {
                ProductCard(Modifier.width(176.dp))
            }
        }

    }
}
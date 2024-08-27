package tm.com.balary.features.product.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.placeholder
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import tm.com.balary.core.loremIpsum
import tm.com.balary.features.product.presentation.ui.detail.ProductDetailScreen
import tm.com.balary.ui.ImageLoader

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    title: String = "Product Name"
) {
    val navigator = LocalNavigator.currentOrThrow
    val shape = RoundedCornerShape(4.dp)
    Column(modifier = modifier.border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(4.dp)
    ).clip(RoundedCornerShape(4.dp)).clickable {
        navigator.push(ProductDetailScreen())
    }) {
        Box(
            Modifier
                .size(176.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape
                )
        ) {
            ImageLoader(
                modifier = Modifier.fillMaxSize(),
                url = "",
                contentScale = ContentScale.Inside
            )

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FavoriteButton(
                    modifier = Modifier,
                    isLiked = false
                )

                ProductTag(
                    modifier = Modifier,
                    text = "Arzanladyş"
                )
            }
        }



        Column(Modifier.fillMaxWidth().padding(4.dp).background(
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            shape = RoundedCornerShape(4.dp)
        ).padding(2.dp)) {
            Spacer(Modifier.height(10.dp))
            ProductPrice(
                modifier = Modifier.fillMaxWidth(),
                price = 170.0,
                oldPrice = 190.0,
                discount = 5.0
            )
            Spacer(Modifier.height(10.dp))

            Text(
                title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W900
                ),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(8.dp))

            Text(
                loremIpsum,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W700,
                    fontSize = 10.sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(6.dp))
            ProductCount(
                modifier = Modifier.fillMaxWidth(),
                star = 4.3,
                commentCount = 123
            )
            Spacer(Modifier.height(6.dp))
            ProductBasketButton(modifier = Modifier.fillMaxWidth())
        }
    }
}
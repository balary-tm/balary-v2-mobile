package tm.com.balary.features.basket.presentation.ui.basket_changes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.LocalStrings
import tm.com.balary.features.basket.data.entity.CheckOrderResponse
import tm.com.balary.features.basket.data.local.BasketLocalEntity
import tm.com.balary.locale.translateValue
import tm.com.balary.ui.ImageLoader

@Composable
fun ChangeItem(
    modifier: Modifier = Modifier,
    item: CheckOrderResponse,
    order: BasketLocalEntity?
) {
    val strings = LocalStrings.current
    Row(
        modifier = modifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
            shape = RoundedCornerShape(10.dp)
        ).clip(RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ImageLoader(
            modifier = Modifier.width(115.dp).height(115.dp).clip(
                RoundedCornerShape(10.dp)
            ).background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(10.dp)
            ),
            url = order?.thumbnail?:"",
            contentScale = ContentScale.Inside
        )

        Column(Modifier.weight(1f)) {
            Text(
                translateValue(order, "title"),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W900
                ),
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.Center
            )

            Text(
                translateValue(order,"description"),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )

            Spacer(Modifier.height(8.dp))

            if(item.out_of_stock) {
                Text(
                    strings.out_of_stock,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            } else if(item.cart_quantity!=item.new_quantity) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        strings.oldCount,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Text(
                        "${item.cart_quantity}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W900
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        strings.newCount,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Text(
                        "${item.new_quantity}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W900
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            } else if(item.cart_discount_price!=item.new_discount_price) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        strings.oldPrice,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Text(
                        "${item.cart_discount_price} m.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W900
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        strings.newPrice,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Text(
                        "${item.new_discount_price} m.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W900
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
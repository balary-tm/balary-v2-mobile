package tm.com.balary.features.order.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import tm.com.balary.ui.ImageLoader

enum class OrderStatus {
    NONE,
    DELIVERED,
    REJECTED,
    PENDING,
    ON_THE_WAY
}

@Composable
fun OrderItem(
    modifier: Modifier = Modifier,
    status: OrderStatus = OrderStatus.NONE
) {
    val navigator = LocalNavigator.currentOrThrow
    val color = when (status) {
        OrderStatus.NONE -> MaterialTheme.colorScheme.secondary
        OrderStatus.DELIVERED -> MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
        OrderStatus.REJECTED -> MaterialTheme.colorScheme.errorContainer
        OrderStatus.PENDING -> MaterialTheme.colorScheme.secondary
        OrderStatus.ON_THE_WAY -> MaterialTheme.colorScheme.tertiary
    }

    Column(
        modifier.border(
            color = MaterialTheme.colorScheme.outline,
            width = 1.dp,
            shape = RoundedCornerShape(10.dp)
        ).clip(RoundedCornerShape(10.dp)).clickable {
            navigator.push(OrderDetails())
        },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            "03 Aprel 2024 12:10",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Row(
            Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Sargyt №: #26491",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W700),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    "Salgy: Mir 7/4 42-jaý, 12 öý",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Box(
                Modifier.background(
                    color = color,
                    shape = RoundedCornerShape(4.dp)
                ).clip(RoundedCornerShape(4.dp)).padding(vertical = 4.dp, horizontal = 15.dp)
            ) {
                androidx.compose.material.Text(
                    "Eltip berildi",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W700
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        LazyRow(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(20) {
                ImageLoader(
                    modifier = Modifier.width(60.dp).height(75.dp).clip(
                        RoundedCornerShape(10.dp)
                    ).border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(10.dp)
                    ),
                    url = ""
                )
            }
        }

        Spacer(Modifier.height(8.dp))


    }
}
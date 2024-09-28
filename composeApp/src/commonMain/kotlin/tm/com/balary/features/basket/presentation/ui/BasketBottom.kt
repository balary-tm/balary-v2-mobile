package tm.com.balary.features.basket.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import tm.com.balary.features.basket.presentation.ui.basket_changes.ChangesDialog
import tm.com.balary.features.basket.presentation.ui.order.OrderScreen
import tm.com.balary.router.BasketDetailScreen

@Composable
fun BasketBottom(
    modifier: Modifier = Modifier,
    total: Double = 0.0,
    navHostController: NavHostController
) {
    val show = remember {
        mutableStateOf(false)
    }

    val strings = LocalStrings.current


    ChangesDialog(
        show = show.value,
        onDismiss = {
            show.value = false
            navHostController.navigate(BasketDetailScreen)
        }
    )

    Column(
        modifier.fillMaxWidth().background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            )
        ).padding(vertical = 8.dp, horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                Icons.Filled.CheckCircle,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "delivery status"
            )
            Text(
                strings.minimumPriceMessage,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W700
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                strings.total,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W900,
                    fontSize = 18.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                "$total m.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W900,
                    fontSize = 18.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                show.value = true
            }
        ) {
            Text(
                strings.confirmOrder,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W700
                ),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
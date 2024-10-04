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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tm.com.balary.features.basket.presentation.ui.basket_changes.ChangesDialog
import tm.com.balary.features.basket.presentation.ui.order.OrderScreen
import tm.com.balary.features.basket.presentation.viewmodel.BasketViewModel
import tm.com.balary.router.BasketDetailScreen
import tm.com.balary.state.LocalDarkMode

@OptIn(KoinExperimentalAPI::class)
@Composable
fun BasketBottom(
    modifier: Modifier = Modifier,
    total: Double = 0.0,
    minPrice: Double = Double.MAX_VALUE,
    freeDeliveryPrice: Double = Double.MAX_VALUE,
    navHostController: NavHostController
) {
    val basketViewModel: BasketViewModel = koinNavViewModel()
    val checkOrderState = basketViewModel.checkOrderState.collectAsState()
    val show = remember {
        mutableStateOf(false)
    }

    val isDark = LocalDarkMode.current
    val toaster = rememberToasterState()
    Toaster(
        state = toaster,
        darkTheme = isDark.value,
        richColors = true,
        alignment = Alignment.TopCenter
    )

    val strings = LocalStrings.current

    val isOk = rememberSaveable(total, minPrice) {
        mutableStateOf(total>=minPrice)
    }

    ChangesDialog(
        show = show.value,
        changes = checkOrderState.value.result?: emptyList(),
        onDismiss = {
            basketViewModel.getBasket { ok->
                isOk.value = ok
                show.value = false
                if(ok) {
                    navHostController.navigate(BasketDetailScreen)
                }
            }

        }
    )

    Column(
        modifier.fillMaxWidth().background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            )
        ).padding(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = if(isOk.value) Icons.Filled.CheckCircle else Icons.Filled.Info,
                modifier = Modifier.size(24.dp),
                tint = if(isOk.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                contentDescription = "delivery status"
            )
            Text(
                strings.minimumPriceMessage
                    .replace("{min_price}", minPrice.toString())
                    .replace("{free_delivery_price}", freeDeliveryPrice.toString()),
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
            enabled = isOk.value,
            onClick = {
                basketViewModel.checkOrder(
                    onError = {message->
                       message?.let {
                           toaster.show(message, type = ToastType.Error)
                       }
                    },
                    onSuccess = { changeCount->
                        if(changeCount>0) {
                            show.value = true
                        } else {
                            navHostController.navigate(BasketDetailScreen)
                        }
                    }
                )
            }
        ) {
            if(checkOrderState.value.loading) {
                CircularProgressIndicator(Modifier.size(30.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
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
}
package tm.com.balary.features.basket.presentation.ui.basket_changes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.lyricist.LocalStrings
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tm.com.balary.features.basket.data.entity.CheckOrderResponse
import tm.com.balary.features.basket.presentation.viewmodel.BasketViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ChangesDialog(
    show: Boolean,
    changes: List<CheckOrderResponse>,
    onDismiss: () -> Unit
) {
    val strings = LocalStrings.current
    val basketViewModel: BasketViewModel = koinNavViewModel()
    val orders = basketViewModel.basketState.collectAsState()
    if (show) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            LazyColumn(
                Modifier.fillMaxWidth().padding(
                    vertical = 22.dp
                ).background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                ), contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ), verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        strings.basketChangedMessage,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W700
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                items(changes.count()) { index ->
                    ChangeItem(
                        Modifier.fillMaxWidth(),
                        changes[index],
                        order = try {
                            orders.value.products.find { it.id == changes[index].product_id }
                        } catch (_: Exception) {null}
                    )
                }
                item {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(
                            shape = RoundedCornerShape(4.dp),
                            onClick = {
                                onDismiss()
                            }
                        ) {
                            Spacer(Modifier.width(22.dp))
                            Text(
                                strings.close,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.W700
                                ),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Spacer(Modifier.width(22.dp))
                        }
                    }
                }
            }
        }
    }
}
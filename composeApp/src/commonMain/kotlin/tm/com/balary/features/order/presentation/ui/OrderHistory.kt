package tm.com.balary.features.order.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import tm.com.balary.features.auth.presentation.ui.BackScreen
import tm.com.balary.router.OrderedProductScreen

class OrderHistoryScreen : Screen {
    @Composable
    override fun Content() {
    }
}

@Composable
fun OrderHistory(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val strings = LocalStrings.current
    BackScreen(
        modifier,
        title = strings.myOrders,
        actions = {
            Box(
                Modifier.background(
                    color = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.6f
                    ),
                    shape = RoundedCornerShape(4.dp)
                ).clip(RoundedCornerShape(4.dp)).clickable {
                    navHostController.navigate(OrderedProductScreen)
                }.padding(vertical = 10.dp, horizontal = 4.dp)
            ) {
                Text(
                    strings.boughtProducts,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W700
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        navHostController = navHostController
    ) {

        LazyColumn(
            Modifier.fillMaxSize().background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            ), contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(15) {
                OrderItem(Modifier.fillMaxWidth(), status = OrderStatus.DELIVERED, navHostController = navHostController)
            }
        }

    }
}
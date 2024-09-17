package tm.com.balary.features.order.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import tm.com.balary.features.auth.presentation.ui.BackScreen
import tm.com.balary.features.product.presentation.ui.ProductCard

@Composable
fun OrderedProducts(navHostController: NavHostController) {
    val strings = LocalStrings.current
    BackScreen(Modifier.fillMaxSize(), title = strings.boughtProducts, navHostController = navHostController) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                horizontal = 16.dp, vertical = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(100) {
                ProductCard(Modifier.fillMaxWidth(), navHostController = navHostController)
            }
        }
    }
}
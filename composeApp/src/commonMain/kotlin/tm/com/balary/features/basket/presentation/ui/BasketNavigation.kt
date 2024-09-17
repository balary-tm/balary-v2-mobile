package tm.com.balary.features.basket.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tm.com.balary.features.basket.presentation.ui.order.Order
import tm.com.balary.features.home.presentation.ui.commonRoutes
import tm.com.balary.router.BasketDetailScreen
import tm.com.balary.router.BasketScreen

@Composable
fun BasketNavigation() {
    val navHostController = rememberNavController()
    NavHost(navHostController, startDestination = BasketScreen) {
        composable<BasketScreen> {
            Basket(navHostController = navHostController)
        }
        composable<BasketDetailScreen> {
            Order(navHostController = navHostController)
        }
        commonRoutes(navHostController)
    }
}
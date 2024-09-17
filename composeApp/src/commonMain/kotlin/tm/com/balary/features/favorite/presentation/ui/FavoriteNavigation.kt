package tm.com.balary.features.favorite.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tm.com.balary.features.home.presentation.ui.commonRoutes
import tm.com.balary.router.FavoriteScreen

@Composable
fun FavoriteNavigation() {
    val navHostController = rememberNavController()
    NavHost(navHostController, startDestination = FavoriteScreen) {
        composable<FavoriteScreen> {
            Favorite(
                navHostController = navHostController
            )
        }

        commonRoutes(navHostController)
    }
}
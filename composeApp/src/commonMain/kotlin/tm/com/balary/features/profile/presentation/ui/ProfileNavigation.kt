package tm.com.balary.features.profile.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tm.com.balary.features.home.presentation.ui.commonRoutes
import tm.com.balary.router.ProfileScreen

@Composable
fun ProfileNavigation() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = ProfileScreen) {
        composable<ProfileScreen> {
            Profile(
                navHostController = navHostController
            )
        }

        commonRoutes(navHostController)
    }
}
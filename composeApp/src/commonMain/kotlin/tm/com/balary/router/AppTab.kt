package tm.com.balary.router

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import tm.com.balary.features.basket.presentation.ui.BasketNavigation
import tm.com.balary.features.category.presentation.ui.CategoryNavigator
import tm.com.balary.features.favorite.presentation.ui.FavoriteNavigation
import tm.com.balary.features.home.presentation.ui.HomeNavigation
import tm.com.balary.features.profile.data.setting.AppSettings
import tm.com.balary.features.profile.presentation.ui.ProfileNavigation
import tm.com.balary.state.LocalAppState
import tm.com.balary.state.LocalDarkMode
import tm.com.balary.state.LocalTABNavigator
import tm.com.balary.ui.AppBottomNav


class AppTabScreen : Screen {
    @Composable
    override fun Content() {
        AppTab(Modifier.fillMaxSize())
    }
}

@Composable
fun AppTab(modifier: Modifier = Modifier) {
    val tabs = listOf(
        Router.HOME_ROUTE,
        Router.CATEGORY_ROUTE,
        Router.BASKET_ROUTE,
        Router.FAVORITE_ROUTE,
        Router.PROFILE_ROUTE
    )
    val navigator = rememberNavController()


    val isDark = LocalDarkMode.current
    val tabNavigator = LocalTABNavigator.current

    val appSettings: AppSettings = koinInject()
    val appState = LocalAppState.current

    LaunchedEffect(true) {
        appSettings.saveIsFirst(false)
        appState.value = appState.value.copy(
            isFirst = false
        )
    }




    Scaffold(
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            AppBottomNav(modifier = Modifier.fillMaxWidth(), tabs = tabs, navigator)
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).consumeWindowInsets(padding)) {
            NavHost(navController = navigator, startDestination = Router.HOME_ROUTE) {
                composable(Router.HOME_ROUTE) {
                    HomeNavigation()
                }

                composable(Router.CATEGORY_ROUTE) {
                    CategoryNavigator()
                }

                composable(Router.BASKET_ROUTE) {
                    BasketNavigation()
                }

                composable(Router.FAVORITE_ROUTE) {
                    FavoriteNavigation()
                }

                composable(Router.PROFILE_ROUTE) {
                    ProfileNavigation()
                }

            }

        }
    }
}
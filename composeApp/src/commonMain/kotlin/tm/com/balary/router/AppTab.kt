package tm.com.balary.router

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import tm.com.balary.features.basket.presentation.ui.BasketTab
import tm.com.balary.features.category.presentation.ui.CategoryTab
import tm.com.balary.features.favorite.presentation.ui.FavoriteTab
import tm.com.balary.features.home.presentation.ui.HomeScreen
import tm.com.balary.features.home.presentation.ui.HomeTab
import tm.com.balary.features.profile.presentation.ui.ProfileTab
import tm.com.balary.state.LocalDarkMode
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
        HomeTab,
        CategoryTab,
        BasketTab,
        FavoriteTab,
        ProfileTab
    )
    val isDark = LocalDarkMode.current
    TabNavigator(HomeTab) {
        key(isDark.value) {
            Scaffold(
                modifier = modifier,
                backgroundColor = MaterialTheme.colorScheme.surface,
                bottomBar = {
                    AppBottomNav(modifier = Modifier.fillMaxWidth(), tabs = tabs)
                }
            ) { padding->
                Box(modifier = Modifier.padding(padding).consumeWindowInsets(padding)) {
                    CurrentTab()
                }
            }
        }
    }
}
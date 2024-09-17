package tm.com.balary.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import tm.com.balary.features.basket.presentation.ui.BasketTab
import tm.com.balary.features.category.presentation.ui.CategoryTab
import tm.com.balary.features.favorite.presentation.ui.FavoriteTab
import tm.com.balary.features.home.presentation.ui.HomeTab
import tm.com.balary.features.profile.presentation.ui.ProfileTab
import tm.com.balary.state.LocalTABNavigator

@Composable
fun AppBottomNav(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    navHostController: NavHostController
) {
    val options = listOf(
        HomeTab,
        CategoryTab,
        BasketTab,
        FavoriteTab,
        ProfileTab
    )
    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
    NavigationBar(modifier = modifier, containerColor = MaterialTheme.colorScheme.surface, tonalElevation = 0.dp) {
        tabs.forEachIndexed { index, tab ->
            val option = options[index]
            val selected = tab == navBackStackEntry.value?.destination?.route
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.outlineVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.outlineVariant
                ),
                selected = selected,
                onClick = {
                    navHostController.navigate(tab) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = option.options.icon!!,
                        contentDescription = option.options.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = option.options.title,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 10.sp,
                            fontWeight = if(selected) FontWeight.W900 else FontWeight.W400
                        )
                    )
                }
            )
        }
    }
}
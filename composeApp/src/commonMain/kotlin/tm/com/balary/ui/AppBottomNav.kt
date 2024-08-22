package tm.com.balary.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab

@Composable
fun AppBottomNav(
    modifier: Modifier = Modifier,
    tabs: List<Tab>
) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBar(modifier = modifier, containerColor = MaterialTheme.colorScheme.surface, tonalElevation = 0.dp) {
        tabs.forEachIndexed { _, tab ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.outlineVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.outlineVariant
                ),
                selected = tab == tabNavigator.current,
                onClick = {
                    tabNavigator.current = tab
                },
                icon = {
                    Icon(
                        painter = tab.options.icon!!,
                        contentDescription = tab.options.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = tab.options.title,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 10.sp,
                            fontWeight = if(tab == tabNavigator.current) FontWeight.W900 else FontWeight.W400
                        )
                    )
                }
            )
        }
    }
}
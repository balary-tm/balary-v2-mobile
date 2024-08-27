import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext
import tm.com.balary.features.basket.presentation.ui.BasketTab
import tm.com.balary.features.category.presentation.ui.CategoryTab
import tm.com.balary.features.favorite.presentation.ui.FavoriteTab
import tm.com.balary.features.home.di.homeModule
import tm.com.balary.features.home.presentation.ui.HomeScreen
import tm.com.balary.features.home.presentation.ui.HomeTab
import tm.com.balary.features.profile.presentation.ui.ProfileTab
import tm.com.balary.router.AppTab
import tm.com.balary.state.Composition
import tm.com.balary.state.LocalDarkMode


@Preview()
@Composable
fun App(modifier: Modifier = Modifier) {
    KoinApplication(
        application = {
            modules(
                homeModule
            )
        }
    ) {
        Box(modifier = modifier) {
            Composition {
                AppTheme(darkTheme = LocalDarkMode.current.value) {
                    AppTab(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}
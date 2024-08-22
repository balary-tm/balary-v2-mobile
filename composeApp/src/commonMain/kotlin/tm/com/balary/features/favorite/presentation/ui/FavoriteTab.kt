package tm.com.balary.features.favorite.presentation.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.category
import balary.composeapp.generated.resources.favorite
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.router.Router

object FavoriteTab : Tab {
    @Composable
    override fun Content() {
        Navigator(FavoriteScreen()) {
            SlideTransition(it)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            return TabOptions(
                index = Router.FAVORITE,
                title = "Halanlarym",
                icon = painterResource(Res.drawable.favorite)
            )
        }
}
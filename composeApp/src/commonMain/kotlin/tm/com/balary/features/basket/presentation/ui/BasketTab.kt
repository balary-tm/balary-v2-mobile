package tm.com.balary.features.basket.presentation.ui

import androidx.compose.runtime.Composable
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.basket
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.router.Router

object BasketTab : Tab {

    override val key: ScreenKey
        get() = Router.BASKET_ROUTE

    @Composable
    override fun Content() {
        Navigator(BasketScreen()) {
            SlideTransition(it)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val strings = LocalStrings.current
            return TabOptions(
                index = Router.BASKET,
                title = strings.basket,
                icon = painterResource(Res.drawable.basket)
            )
        }
}
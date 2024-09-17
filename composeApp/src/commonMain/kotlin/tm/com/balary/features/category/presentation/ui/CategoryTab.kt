package tm.com.balary.features.category.presentation.ui

import androidx.compose.runtime.Composable
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.category
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.router.Router

object CategoryTab : Tab {

    override val key: ScreenKey
        get() = Router.CATEGORY_ROUTE

    @Composable
    override fun Content() {
        Navigator(CategoryScreen()) {
            SlideTransition(it)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val strings = LocalStrings.current
            return TabOptions(
                index = Router.CATEGORY,
                title = strings.category,
                icon = painterResource(Res.drawable.category)
            )
        }
}
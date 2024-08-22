package tm.com.balary.features.home.presentation.ui

import androidx.compose.runtime.Composable
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.home
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.router.Router

object HomeTab: Tab {
    @Composable
    override fun Content() {
        Navigator(HomeScreen()) {
            SlideTransition(it)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            return TabOptions(
                index = Router.HOME,
                title = "Esasy sahypa",
                icon = painterResource(Res.drawable.home)
            )
        }
}
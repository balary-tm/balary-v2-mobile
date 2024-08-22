package tm.com.balary.features.basket.presentation.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.basket
import balary.composeapp.generated.resources.category
import balary.composeapp.generated.resources.profile
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.router.Router

object BasketTab : Tab {
    @Composable
    override fun Content() {
        Text("Basket")
    }

    override val options: TabOptions
        @Composable
        get() {
            return TabOptions(
                index = Router.BASKET,
                title = "Sebet",
                icon = painterResource(Res.drawable.basket)
            )
        }
}
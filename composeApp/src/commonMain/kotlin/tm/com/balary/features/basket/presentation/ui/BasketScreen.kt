package tm.com.balary.features.basket.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.basket
import balary.composeapp.generated.resources.delete
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.product.presentation.ui.FilterBar
import tm.com.balary.features.product.presentation.ui.filter.FilterScreen
import tm.com.balary.ui.AppAlert
import tm.com.balary.ui.AppAlertType

class BasketScreen : Screen {
    @Composable
    override fun Content() {
        Basket(Modifier.fillMaxSize())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Basket(modifier: Modifier = Modifier) {

    val show = remember {
        mutableStateOf(false)
    }

    AppAlert(
        show = show.value,
        onDismiss = {
            show.value = false
        },
        title = "Tassyklamak",
        message = buildAnnotatedString {
            append("Siz hakykatdanam sebedi boşatmak isleýärsiňizmi?")
        },
        type = AppAlertType.DANGER
    )

    val navigator = LocalNavigator.currentOrThrow
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    FilterBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Sebet",
                        actionIcon = painterResource(Res.drawable.delete),
                        onFilter = {
                            show.value = true
                        },
                        onBack = {

                        }
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            BasketBottom(Modifier.fillMaxWidth())
        },
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            Modifier.fillMaxSize().padding(vertical = 8.dp).background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
        ) {
            items(5) {
                BasketItem(modifier = Modifier.fillMaxWidth())
            }
            item {
                Column(Modifier.fillMaxWidth().padding(vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    PriceInfo(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Bahasy:",
                        value = "350,00 m."
                    )
                    PriceInfo(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Eltip bermegiň bahasy:",
                        value = "0,00 m."
                    )
                    PriceInfo(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Arzanladyşlar:",
                        value = "-1,00 m.",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            item {
                Spacer(Modifier.height(150.dp))
            }
        }
    }
}
package tm.com.balary.features.basket.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.basket
import balary.composeapp.generated.resources.delete
import balary.composeapp.generated.resources.empty_favs
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tm.com.balary.features.basket.presentation.viewmodel.BasketViewModel
import tm.com.balary.features.product.presentation.ui.FilterBar
import tm.com.balary.ui.AppAlert
import tm.com.balary.ui.AppAlertType
import tm.com.balary.ui.AppError
import tm.com.balary.ui.AppLoading
import tm.com.balary.ui.Empty

class BasketScreen : Screen {
    @Composable
    override fun Content() {
    }
}

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun Basket(modifier: Modifier = Modifier, navHostController: NavHostController) {

    val basketViewModel: BasketViewModel = koinNavViewModel()
    val basketState = basketViewModel.basketState.collectAsState()
    val extraState = basketViewModel.orderExtraState.collectAsState()

    LaunchedEffect(true) {
        basketViewModel.initOrderExtra()
    }


    val show = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        basketViewModel.getBasket()
    }


    val strings = LocalStrings.current

    AppAlert(
        show = show.value,
        onDismiss = {
            show.value = false
        },
        onConfirm = {
            basketViewModel.deleteAll()
        },
        title = strings.confirmation,
        message = buildAnnotatedString {
            append(strings.wantClearBasket)
        },
        type = AppAlertType.DANGER
    )

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    FilterBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = strings.basket,
                        actionIcon = painterResource(Res.drawable.delete),
                        onFilter = {
                            show.value = true
                        },
                        onBack = {
                            navHostController.navigateUp()
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
            if(basketState.value.products.isNotEmpty()) {
                extraState.value.extra?.let { extra ->
                    BasketBottom(
                        Modifier.fillMaxWidth(),
                        navHostController = navHostController,
                        total = basketState.value.calculation.total.plus(extra.delivery_price?:0.0),
                        minPrice = extra.min_total_price?:Double.MAX_VALUE
                    )
                }

            }
        },
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        if(basketState.value.products.isNotEmpty()) {
            if(extraState.value.loading) {
                AppLoading(Modifier.fillMaxSize())
            } else if(extraState.value.error.isNullOrEmpty().not()) {
                AppError(Modifier.fillMaxSize())
            } else {
                extraState.value.extra?.let { extra->
                    LazyColumn(
                        Modifier.fillMaxSize().padding(vertical = 8.dp).background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp)
                        ),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
                    ) {

                        items(basketState.value.products.count()) { index->
                            val item = basketState.value.products[index]
                            BasketItem(
                                modifier = Modifier.fillMaxWidth(),
                                navHostController = navHostController,
                                product = item
                            )
                        }

                        item {
                            Column(Modifier.fillMaxWidth().padding(vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                PriceInfo(
                                    modifier = Modifier.fillMaxWidth(),
                                    title = strings.price,
                                    value = "${basketState.value.calculation.totalWithoutDiscount} m."
                                )
                                PriceInfo(
                                    modifier = Modifier.fillMaxWidth(),
                                    title = strings.deliveryPrice,
                                    value = extra.delivery_price.toString().plus(" m.")
                                )
                                PriceInfo(
                                    modifier = Modifier.fillMaxWidth(),
                                    title = strings.discounts,
                                    value = "-${basketState.value.calculation.discount} m.",
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
        } else {
            Empty(
                modifier = Modifier.fillMaxSize(),
                image = painterResource(Res.drawable.empty_favs),
                text = strings.noData
            )
        }

    }
}
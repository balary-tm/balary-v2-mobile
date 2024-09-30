package tm.com.balary.features.home.presentation.ui.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tm.com.balary.features.ads.presentation.ui.AdsComponent
import tm.com.balary.features.basket.presentation.viewmodel.BasketViewModel
import tm.com.balary.features.favorite.presentation.viewmodel.FavoriteViewModel
import tm.com.balary.features.home.domain.model.SlideModel
import tm.com.balary.features.product.domain.model.ProductModel
import tm.com.balary.features.product.presentation.ui.ProductCard
import tm.com.balary.features.product.presentation.ui.ProductCardSkeleton
import tm.com.balary.locale.translateValue
import tm.com.balary.router.SubCategoryScreen
import tm.com.balary.state.LocalAppNavigator
import tm.com.balary.state.LocalHomeNavigator
import tm.com.balary.ui.skeleton.SkeletonRounded

@Composable
fun HomeSectionSkeleton(
    modifier: Modifier = Modifier,
    adsCount: Int = 1,
    productCount: Int = 5,
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                horizontal = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(adsCount) {
                SkeletonRounded(Modifier.weight(1f).height(140.dp), borderRadius = 10.dp)
            }
        }
        Spacer(Modifier.height(12.dp))
        SkeletonRounded(Modifier.fillMaxWidth().height(40.dp).padding(horizontal = 16.dp), borderRadius = 10.dp)
        Spacer(Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(productCount) {
                ProductCardSkeleton(Modifier.width(176.dp))
            }
        }
    }
}

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    title: String,
    adsCount: Int = 0,
    slides: List<SlideModel> = emptyList(),
    products: List<ProductModel> = emptyList(),
    categoryId: String? = null,
    navHostController: NavHostController
) {
    val basketViewModel: BasketViewModel = koinNavViewModel()
    val basketState = basketViewModel.basketState.collectAsState()
    LaunchedEffect(true) {
        basketViewModel.getBasket()
    }
    val favoriteViewModel: FavoriteViewModel = koinNavViewModel()
    Column(
        modifier = modifier.padding(vertical = 16.dp)
    ) {
        if(slides.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(
                    horizontal = 16.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(adsCount) { index->
                    val slide = slides[index]
                    AdsComponent(
                        modifier = Modifier.weight(1f).height(140.dp),
                        image = slide.imageUrl,
                        slideType = slide.slideType
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
        }
        HomeProductSection(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), title, onClick = {
                navHostController.navigate(SubCategoryScreen(categoryId?:""))
            })
        Spacer(Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products.count()) { index->
                val product = products[index]
                ProductCard(
                    Modifier.width(176.dp),
                    title = translateValue(product,"title"),
                    navHostController = navHostController,
                    productModel = product,
                    basketList = basketState.value.products,
                    onBasketAdd = { item->
                        basketViewModel.addBasket(item)
                    },
                    favoriteViewModel = favoriteViewModel
                )
            }
        }

    }
}
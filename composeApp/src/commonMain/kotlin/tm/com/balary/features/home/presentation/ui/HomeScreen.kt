package tm.com.balary.features.home.presentation.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import com.valentinilk.shimmer.shimmer
import org.koin.compose.viewmodel.koinViewModel
import tm.com.balary.features.ads.presentation.ui.PopupAds
import tm.com.balary.features.ads.presentation.ui.SheetAds
import tm.com.balary.features.home.domain.model.SlideModel
import tm.com.balary.features.home.presentation.ui.banner.Banner
import tm.com.balary.features.home.presentation.ui.banner.HomeToolbar
import tm.com.balary.features.home.presentation.ui.banner.SearchInput
import tm.com.balary.features.home.presentation.ui.brand.Brands
import tm.com.balary.features.home.presentation.ui.category.HomeCategory
import tm.com.balary.features.home.presentation.ui.product.HomeSection
import tm.com.balary.features.home.presentation.ui.product.HomeSectionSkeleton
import tm.com.balary.features.home.presentation.ui.version.UpdateAppDialog
import tm.com.balary.features.home.presentation.viewmodel.HomeViewModel
import tm.com.balary.locale.translateValue

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        Home()
    }

}

@Composable
fun Home(navHostController: NavHostController = rememberNavController()) {

    val lazyScroll = rememberLazyListState()

    val homeViewModel: HomeViewModel = koinViewModel()

    val productState = homeViewModel.homeProducts.collectAsState()
    val categoryState = homeViewModel.categoryState.collectAsState()
    val brandState = homeViewModel.brandState.collectAsState()
    val versionState = homeViewModel.versionCheckState.collectAsState()
    val adsState = homeViewModel.adsState.collectAsState()



    val showPopup = rememberSaveable {
        mutableStateOf(true)
    }

    val showSheetAds = rememberSaveable {
        mutableStateOf(false)
    }

    val showUpdate = rememberSaveable {
        mutableStateOf(true)
    }

    val strings = LocalStrings.current

    adsState.value.ads?.second?.let { sheet ->
        SheetAds(
            show = showSheetAds.value,
            ads = sheet,
            onClose = {
                showSheetAds.value = false
            }
        )
    }


    adsState.value.ads?.let { pair->
        if(pair.first!=null) {
            pair.first?.let { popup->
                PopupAds(
                    show = showPopup.value,
                    ads = popup,
                    onClose = {
                        showSheetAds.value = pair.second!=null
                        showPopup.value = false
                    }
                )
            }
        } else {
            showSheetAds.value = pair.second!=null
        }

    }





    versionState.value.versions?.let { versions->
        if(versions.isNotEmpty()) {
            UpdateAppDialog(
                show = showUpdate.value,
                versions = versions,
                onClose = {
                    showUpdate.value = false
                }
            )
        }
    }






    val animatedOffset = animateDpAsState(if(lazyScroll.firstVisibleItemIndex > 0) 0.dp else (-100).dp)



    Box(
        modifier = Modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.background
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().zIndex(2f).offset(y = animatedOffset.value).background(Color.Transparent).background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)
            ).clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)).padding(16.dp)
        ) {
            SearchInput(
                modifier = Modifier.fillMaxWidth(),
                onSearch = { query->

                }
            )
        }
        LazyColumn (
            Modifier.fillMaxWidth(),
            state = lazyScroll,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                HomeToolbar(modifier = Modifier.fillMaxWidth())
            }
            item {
                Column(
                    Modifier.fillMaxWidth().background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                    )
                ) {
                    Spacer(Modifier.height(8.dp))
                    Banner(Modifier.fillMaxWidth())
//                    HomeCategory(Modifier.fillMaxWidth())
                    Spacer(Modifier.height(8.dp))
                }
            }
            if(productState.value.loading) {
                item {
                    HomeSectionSkeleton(
                        modifier = Modifier.fillMaxWidth(),
                        adsCount = 2
                    )
                }
                item {
                    HomeSectionSkeleton(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            } else {
                productState.value.productModel?.let { data ->
                    item {
                        HomeSection(
                            Modifier.fillMaxWidth(),
                            title = strings.discountProducts,
                            adsCount = data.discountSlides.count(),
                            products = data.discountProducts,
                            slides = data.discountSlides,
                            navHostController = navHostController,
                            categoryId = null
                        )
                    }
                    item {
                        HomeSection(
                            Modifier.fillMaxWidth(),
                            title = strings.newProducts,
                            adsCount = 0,
                            products = data.newProducts,
                            navHostController = navHostController,
                            categoryId = null
                        )
                    }
                }
            }
            if(categoryState.value.loading) {
                items(5) {
                    HomeSectionSkeleton(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            } else {
                categoryState.value.category?.let { categories->
                    items(categories.count()) { index ->
                        val category = categories[index]
                        HomeSection(
                            Modifier.fillMaxWidth(),
                            title = translateValue(category,"title"),
                            adsCount = 1,
                            products = category.products,
                            slides = listOf(
                                SlideModel(
                                    name = category.slide,
                                    imageUrl = category.slide,
                                    slideType = category.slideType
                                )
                            ),
                            navHostController = navHostController,
                            categoryId = category.id.toString()
                        )
                    }
                }
            }

            if(brandState.value.loading) {
                item {
                    HomeSectionSkeleton(Modifier.fillMaxWidth(), adsCount = 0, productCount = 0)
                }
            } else {
                brandState.value.brand?.let { brands->
                    if(brands.isNotEmpty()) {
                        item {
                            Brands(Modifier.fillMaxWidth(), brands)
                        }
                    }
                }
            }
            item {
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
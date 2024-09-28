package tm.com.balary.features.product.presentation.ui.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.empty_favs
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tm.com.balary.features.basket.presentation.viewmodel.BasketViewModel
import tm.com.balary.features.category.presentation.ui.subcategory.ProductMiniCategory
import tm.com.balary.features.category.presentation.ui.subcategory.ProductMiniCategorySkeleton
import tm.com.balary.features.category.presentation.viewmodel.CategoryViewModel
import tm.com.balary.features.home.presentation.ui.banner.SearchInput
import tm.com.balary.features.product.presentation.ui.FilterBar
import tm.com.balary.features.product.presentation.ui.ProductCard
import tm.com.balary.features.product.presentation.ui.ProductCardSkeleton
import tm.com.balary.features.product.presentation.ui.filter.FilterScreen
import tm.com.balary.features.product.presentation.viewmodel.ProductViewModel
import tm.com.balary.locale.translateValue
import tm.com.balary.router.ProductsScreen
import tm.com.balary.ui.Empty

class ProductListScreen : Screen {
    @Composable
    override fun Content() {
    }
}

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    productArgs: ProductsScreen
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val categoryViewModel: CategoryViewModel = koinViewModel()
    val productViewModel: ProductViewModel = koinViewModel()
    val categories = categoryViewModel.childCategoryState.collectAsState()
    val productState = productViewModel.productsState.collectAsState()
    val productRequest = productViewModel.body.value
    val currentCategory = rememberSaveable {
        mutableStateOf("")
    }
    val categoryScrollController = rememberLazyListState()
    val basketViewModel: BasketViewModel = koinNavViewModel()
    val basketState = basketViewModel.basketState.collectAsState()
    LaunchedEffect(true) {
        basketViewModel.getBasket()
    }
    LaunchedEffect(productArgs) {
        if (productArgs.categoryId.isNullOrEmpty().not()) {
            categoryViewModel.initChildCategories(productArgs.categoryId)
        }
        productViewModel.initProducts(productArgs.categoryId)
    }
    Column(
        Modifier.fillMaxWidth().background(
            color = MaterialTheme.colorScheme.background
        )
    ) {
        SearchInput(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)
                .padding(
                    top = 12.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 2.dp
                ),
            onSearch = { query ->

            }
        )

        Scaffold(
            backgroundColor = MaterialTheme.colorScheme.background,
            modifier = modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                Column(
                    Modifier.fillMaxWidth().background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CenterAlignedTopAppBar(
                        title = {
                            Column(Modifier.fillMaxWidth()) {
                                FilterBar(
                                    modifier = Modifier.fillMaxWidth(),
                                    title = translateValue(productArgs,"title"),
                                    onFilter = {
                                        navHostController.navigate(tm.com.balary.router.FilterScreen)
                                    },
                                    onBack = {
                                        navHostController.navigateUp()
                                    }
                                )

                            }
                        },
                        scrollBehavior = scrollBehavior,
                        colors = TopAppBarDefaults.topAppBarColors(
                            scrolledContainerColor = Color.Transparent,
                            containerColor = Color.Transparent
                        )
                    )

                    if (categories.value.loading) {
                        ProductMiniCategorySkeleton(Modifier.fillMaxWidth())
                    } else {
                        categories.value.category?.let { categories ->
                            LaunchedEffect(currentCategory.value) {
                                val index =
                                    categories.categories.indexOfFirst { it.id.toString() == currentCategory.value }
                                if (index >= 0 && index < categories.categories.count()) {
                                    categoryScrollController.scrollToItem(index)
                                }
                            }
                            LazyRow(
                                state = categoryScrollController,
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(categories.categories.count()) { index ->
                                    val model = categories.categories[index]
                                    ProductMiniCategory(
                                        model = model,
                                        selected = currentCategory.value == model.id.toString(),
                                        navHostController = navHostController,
                                    )
                                }
                            }
                        }
                    }

                    Spacer(Modifier.fillMaxWidth())
                }
            }
        ) {
            if (productState.value.loading.not() && productRequest.page >= 1 && (productState.value.products == null || productState.value.products?.products.isNullOrEmpty())) {
                Empty(
                    Modifier.fillMaxSize()
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (productState.value.loading && productRequest.page <= 1) {
                    items(20) {
                        ProductCardSkeleton(Modifier.fillMaxWidth())
                    }
                }
                productState.value.products?.let { list ->
                    items(list.products.count()) { index ->
                        val product = list.products[index]
                        currentCategory.value = product.category_id?.toString() ?: ""
                        ProductCard(
                            modifier = Modifier.fillMaxWidth(),
                            navHostController = navHostController,
                            title = translateValue(product, "title"),
                            productModel = product,
                            basketList = basketState.value.products,
                            onBasketAdd = {item->
                                basketViewModel.addBasket(item)
                            }
                        )
                    }
                    item(
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        if (productState.value.hasMore) {
                            Box(
                                Modifier.fillMaxWidth().padding(6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(Modifier.size(35.dp))
                            }
                        }
                        productViewModel.nextPageProducts()
                    }
                }

            }
        }
    }
}
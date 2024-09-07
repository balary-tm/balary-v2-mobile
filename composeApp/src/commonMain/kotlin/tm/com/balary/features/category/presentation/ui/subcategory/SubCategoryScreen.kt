package tm.com.balary.features.category.presentation.ui.subcategory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.Flow
import tm.com.balary.features.category.presentation.ui.CategoryItem
import tm.com.balary.features.home.presentation.ui.banner.SearchInput
import tm.com.balary.features.home.presentation.ui.product.HomeProductSection
import tm.com.balary.features.product.presentation.ui.FilterBar
import tm.com.balary.features.product.presentation.ui.ProductCard
import tm.com.balary.features.product.presentation.ui.filter.FilterScreen
import tm.com.balary.features.product.presentation.ui.products.ProductList
import tm.com.balary.features.product.presentation.ui.products.ProductListScreen
import tm.com.balary.ui.VerticalGrid

class SubCategoryScreen : Screen {
    @Composable
    override fun Content() {
        SubCategory()
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SubCategory(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.currentOrThrow
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
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
            backgroundColor = MaterialTheme.colorScheme.surface,
            modifier = modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        FilterBar(
                            modifier = Modifier.fillMaxWidth(),
                            title = "Miweler, gök önümler, işdäaçarlar",
                            onFilter = {
                                navigator.push(FilterScreen())
                            },
                            onBack = {

                            }
                        )
                    },
                    scrollBehavior = scrollBehavior,
                    colors = TopAppBarDefaults.topAppBarColors(
                        scrolledContainerColor = Color.Transparent,
                        containerColor = Color.Transparent
                    )
                )
            }
        ) {


            LazyColumn(Modifier.fillMaxSize()) {
                item {
                    Box(
                        Modifier.fillMaxWidth().background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(
                                bottomStart = 20.dp,
                                bottomEnd = 20.dp
                            )
                        ).padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            maxItemsInEachRow = 3,
                        ) {
                            repeat(10) {
                                CategoryItem(
                                    modifier = Modifier.fillMaxWidth(0.3f),
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    image = "",
                                    title = "Işdäaçar, duzlanan önümler",
                                    onClick = {
                                        navigator.push(ProductListScreen())
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }


    }

}
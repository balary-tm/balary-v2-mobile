package tm.com.balary.features.category.presentation.ui.subcategory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tm.com.balary.features.category.domain.model.CategoryModel
import tm.com.balary.features.category.presentation.ui.CategoryItem
import tm.com.balary.features.category.presentation.viewmodel.CategoryViewModel
import tm.com.balary.features.home.presentation.ui.banner.SearchInput
import tm.com.balary.features.product.presentation.ui.FilterBar
import tm.com.balary.locale.translateValue
import tm.com.balary.router.ProductsScreen
import tm.com.balary.router.SubCategoryScreen
import tm.com.balary.state.LocalHomeNavigator
import tm.com.balary.ui.AppError
import tm.com.balary.ui.AppLoading

class SubCategoryScreen : Screen {
    @Composable
    override fun Content() {
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SubCategory(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    parentId: String? = null
) {
    val categoryViewModel: CategoryViewModel = koinViewModel()
    val state = categoryViewModel.childCategoryState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    LaunchedEffect(parentId) {
        categoryViewModel.initChildCategories(parentId)
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

        if(state.value.loading) {
            AppLoading(Modifier.fillMaxSize())
        } else if(state.value.error.isNullOrEmpty().not()) {
            AppError(Modifier.fillMaxSize())
        } else {
            if(state.value.category==null || state.value.category?.categories.isNullOrEmpty()) {
                navHostController.navigate(
                    ProductsScreen(
                        parentId.toString(),
                        title_ru = state.value.category?.parent?.title_ru?:"",
                        title_tm = state.value.category?.parent?.title_tm?:"",
                    )
                ) {
                    popUpTo<SubCategoryScreen> {
                        inclusive = true
                    }
                }
            }
            state.value.category?.let { data->
                Scaffold(
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    modifier = modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                FilterBar(
                                    modifier = Modifier.fillMaxWidth(),
                                    title = translateValue(data.parent, "title"),
                                    onFilter = {
                                        navHostController.navigate(tm.com.balary.router.FilterScreen)
                                    },
                                    onBack = {
                                        navHostController.navigateUp()
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
                                    repeat(data.categories.count()) { index->
                                        val item = data.categories[index]
                                        CategoryItem(
                                            modifier = Modifier.fillMaxWidth(0.3f),
                                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                                            image = item.icon_sm?:"",
                                            title = translateValue(item,"title"),
                                            onClick = {
                                                navHostController.navigate(
                                                    ProductsScreen(
                                                        item.id.toString(),
                                                        title_ru = item.title_ru?:"",
                                                        title_tm = item.title_tm?:""
                                                    )
                                                )
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


    }

}
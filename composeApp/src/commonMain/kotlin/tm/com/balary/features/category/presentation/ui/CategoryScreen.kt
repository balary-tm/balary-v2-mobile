package tm.com.balary.features.category.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tm.com.balary.features.category.presentation.ui.subcategory.SubCategoryScreen
import tm.com.balary.features.category.presentation.viewmodel.CategoryViewModel
import tm.com.balary.features.home.presentation.ui.banner.SearchInput
import tm.com.balary.locale.translateValue


class CategoryScreen : Screen {
    @Composable
    override fun Content() {
    }

}

@OptIn(KoinExperimentalAPI::class)
@Composable
fun Category(navHostController: NavHostController) {
    val categoryViewModel: CategoryViewModel = koinNavViewModel()
    val parentCategoryState = categoryViewModel.parentCategoryState.collectAsState()
    LaunchedEffect(true) {
        categoryViewModel.initCategories()
    }
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(Modifier.padding(16.dp)) {
                SearchInput(
                    modifier = Modifier.fillMaxWidth(),
                    onSearch = { query->

                    }
                )
            }
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if(parentCategoryState.value.loading) {
                items(30) {
                    CategoryItemSkeleton(Modifier.fillMaxWidth())
                }
            } else {
                parentCategoryState.value.categories?.let { list->
                    items(list.count()) { index->
                        val item = list[index]
                        CategoryItem(
                            modifier = Modifier.fillMaxWidth(),
                            title = translateValue(item,"title"),
                            containerColor = MaterialTheme.colorScheme.surfaceBright,
                            image = item.icon_sm?:"",
                            onClick = {
                                navHostController.navigate(tm.com.balary.router.SubCategoryScreen(item.id.toString()))
                            }
                        )
                    }
                }
            }

        }
    }
}
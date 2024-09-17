package tm.com.balary.features.favorite.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import tm.com.balary.features.home.presentation.ui.banner.SearchInput
import tm.com.balary.features.product.presentation.ui.FilterBar
import tm.com.balary.features.product.presentation.ui.ProductCard
import tm.com.balary.features.product.presentation.ui.filter.FilterScreen

class FavoriteScreen : Screen {
    @Composable
    override fun Content() {
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favorite(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val strings = LocalStrings.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Column(
        modifier.fillMaxSize().background(
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
                CenterAlignedTopAppBar(
                    title = {
                        FilterBar(
                            modifier = Modifier.fillMaxWidth(),
                            title = strings.favorites,
                            onFilter = {
                                navHostController.navigate(tm.com.balary.router.FilterScreen)
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
            }
        ) {

//            Box(Modifier.fillMaxSize()) {
//                Empty(
//                    modifier = Modifier.align(Alignment.Center),
//                    image = painterResource(Res.drawable.empty_favs),
//                    text = "Halan harytlaryňyz tapylmady!"
//                )
//            }
            LazyVerticalGrid(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 8.dp
                ).background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                ),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(100) {
                    ProductCard(
                        modifier = Modifier.fillMaxWidth(),
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}
package tm.com.balary.features.home.presentation.ui.banner

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import tm.com.balary.features.home.domain.model.BannerModel
import tm.com.balary.features.home.presentation.viewmodel.HomeViewModel
import tm.com.balary.features.product.presentation.ui.detail.Indicator
import tm.com.balary.ui.ImageLoader

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Banner(modifier: Modifier = Modifier) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val bannerState = homeViewModel.bannerState.collectAsState()
    LaunchedEffect(true) {
        homeViewModel.initBanners()
    }
    if(bannerState.value.loading) {
        LinearProgressIndicator(Modifier.fillMaxWidth())
    } else {
        bannerState.value.banners?.let { list->
            val pager = rememberPagerState { list.count() }
            Box(modifier) {
                HorizontalPager(
                    pager,
                    modifier = Modifier.fillMaxWidth(),
                    pageSpacing = 8.dp,
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) { index ->
                    BannerItem(Modifier.fillMaxWidth(), list[index])
                }
                Indicator(
                    modifier = Modifier.align(Alignment.BottomCenter).offset(
                        y = (-8).dp
                    ),
                    passiveColor = Color.White,
                    count = list.count(),
                    current = pager.currentPage
                )
            }
        }
    }
}

@Composable
fun BannerItem(
    modifier: Modifier = Modifier,
    bannerModel: BannerModel
) {
    Box(
        modifier.clip(RoundedCornerShape(12.dp)).height(140.dp).background(
            color = MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(12.dp)
        )
    ) {
        ImageLoader(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            url = bannerModel.image
        )
    }
}
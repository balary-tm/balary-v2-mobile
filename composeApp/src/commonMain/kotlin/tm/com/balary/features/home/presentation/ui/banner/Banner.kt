package tm.com.balary.features.home.presentation.ui.banner

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import chaintech.videoplayer.model.PlayerConfig
import chaintech.videoplayer.ui.video.VideoPlayerView
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import tm.com.balary.features.home.domain.model.BannerModel
import tm.com.balary.features.home.presentation.viewmodel.HomeViewModel
import tm.com.balary.features.product.presentation.ui.detail.Indicator
import tm.com.balary.ui.ImageLoader
import tm.com.balary.ui.skeleton.SkeletonRounded

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Banner(modifier: Modifier = Modifier) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val bannerState = homeViewModel.bannerState.collectAsState()
    LaunchedEffect(true) {
        homeViewModel.initBanners()
    }
    if(bannerState.value.loading) {
        BannerItemSkeleton(Modifier.fillMaxWidth().padding(horizontal = 16.dp))
    } else {
        bannerState.value.banners?.let { list->
            val pager = rememberPagerState { Int.MAX_VALUE }
            LaunchedEffect(key1 = Unit, block = {
                var initPage = Int.MAX_VALUE / 2
                while (initPage % list.size != 0) {
                    initPage++
                }
                pager.scrollToPage(initPage)
            })
            Box(modifier) {
                    HorizontalPager(
                        pager,
                        modifier = Modifier.fillMaxWidth(),
                        pageSpacing = 8.dp,
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) { index ->
                        list.getOrNull(
                            index % (list.size)
                        )?.let { item ->
                            BannerItem(Modifier.fillMaxWidth(), item)
                        }
                    }
                Indicator(
                    modifier = Modifier.align(Alignment.BottomCenter).offset(
                        y = (-8).dp
                    ),
                    passiveColor = Color.White,
                    count = list.count(),
                    current = pager.currentPage % (list.size)
                )
            }
        }
    }
}

@Composable
fun BannerItemSkeleton(modifier: Modifier = Modifier) {
    SkeletonRounded(
        modifier = modifier.height(140.dp),
        borderRadius = 14.dp
    )
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
        when(bannerModel.media_type){
            "video" -> {
                val play = rememberSaveable {
                    mutableStateOf(false)
                }
                if(play.value) {
                    VideoPlayerView(
                        modifier = Modifier.fillMaxSize(),
                        url = bannerModel.media_path?:"",
                        playerConfig = PlayerConfig(
                            isSpeedControlEnabled = false,
                            isScreenResizeEnabled = false,
                            isFullScreenEnabled = false,
                            isSeekBarVisible = false,
                            isScreenLockEnabled = false,
                            isDurationVisible = false,
                            isAutoHideControlEnabled = false,
                            isFastForwardBackwardEnabled = false,
                            isPauseResumeEnabled = false
                        )
                    )
                } else {
                    ImageLoader(
                        modifier = Modifier.fillMaxSize().clickable {
                            play.value = true
                        },
                        contentScale = ContentScale.FillBounds,
                        url = bannerModel.thumbnail_path?:""
                    )
                }
            }
            else -> {
                ImageLoader(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    url = bannerModel.media_path?:""
                )
            }
        }
    }
}
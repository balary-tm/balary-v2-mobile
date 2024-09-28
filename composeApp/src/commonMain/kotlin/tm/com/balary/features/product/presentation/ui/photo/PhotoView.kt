package tm.com.balary.features.product.presentation.ui.photo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.close_filled
import balary.composeapp.generated.resources.placeholder
import cafe.adriel.voyager.core.screen.Screen
import chaintech.videoplayer.model.PlayerConfig
import chaintech.videoplayer.ui.video.VideoPlayerView
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.product.data.entity.single.ProductMedia
import tm.com.balary.features.product.presentation.ui.detail.Indicator
import tm.com.balary.ui.ImageLoader

class PhotoViewScreen : Screen {
    @Composable
    override fun Content() {
        PhotoView(Modifier.fillMaxSize())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoViewDialog(
    modifier: Modifier = Modifier,
    show: Boolean = false,
    images: List<ProductMedia> = emptyList(),
    initialIndex: Int = 0,
    onClose: () -> Unit
) {
    if (show) {
        val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            sheetState = state,
            modifier = modifier,
            dragHandle = {},
            containerColor = Color.Black,
            onDismissRequest = {
                onClose()
            }
        ) {
            PhotoView(
                Modifier.fillMaxSize(),
                images = images,
                initialIndex = initialIndex,
                onClose = onClose)
        }
    }
}

@Composable
fun PhotoView(
    modifier: Modifier = Modifier,
    images: List<ProductMedia> = emptyList(),
    initialIndex: Int = 0,
    onClose: () -> Unit = {}) {
    val pagerState = rememberPagerState(initialIndex) { images.count() }
    Box(modifier) {
        HorizontalPager(
            pagerState, contentPadding = PaddingValues(
                horizontal = 16.dp
            ),
            pageSpacing = 12.dp
        ) { index->
            val media = images[index]
            if(media.type == "video") {
                Box(Modifier.fillMaxSize()) {
                    VideoPlayerView(
                        url = media.media_path,
                        playerConfig = PlayerConfig(
                            isPause = true,
                            isSpeedControlEnabled = false,
                            isScreenResizeEnabled = false,
                            isFullScreenEnabled = false,
                            isSeekBarVisible = true,
                            isScreenLockEnabled = false,
                            isDurationVisible = false,
                            isAutoHideControlEnabled = false,
                            isFastForwardBackwardEnabled = false,
                            isPauseResumeEnabled = true,
                            reelVerticalScrolling = false
                        )
                    )
                }
            } else {
                ImageLoader(
                    modifier = Modifier.fillMaxSize().zoomable(rememberZoomState()),
                    url = media.media_path,
                    placeholder = painterResource(Res.drawable.placeholder)
                )
            }

        }

        IconButton(
            modifier = Modifier.align(Alignment.TopEnd).offset(
                x = (-16).dp,
                y = 32.dp
            ),
            onClick = {
                onClose()
            }
        ) {
            Image(
                painter = painterResource(Res.drawable.close_filled),
                contentDescription = "close",
                modifier = Modifier.size(24.dp)
            )
        }

        Indicator(
            modifier = Modifier.align(Alignment.BottomCenter).offset(
                y = (-62).dp
            ),
            count = images.count(),
            current = pagerState.currentPage
        )
    }
}
package tm.com.balary.features.ads.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.placeholder
import chaintech.videoplayer.model.PlayerConfig
import chaintech.videoplayer.ui.video.VideoPlayerView
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.home.domain.model.SlideType
import tm.com.balary.ui.ImageLoader
import tm.com.balary.ui.skeleton.SkeletonRounded

@Composable
fun AdsComponent(
    modifier: Modifier = Modifier,
    image: String,
    slideType: SlideType
) {
    val shape = RoundedCornerShape(10.dp)

    when(slideType) {
        SlideType.IMAGE -> {
            ImageLoader(
                modifier = modifier.clip(shape).background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = shape
                ).clickable {

                },
                url = image,
                placeholder = painterResource(Res.drawable.placeholder),
                contentScale = ContentScale.FillBounds
            )
        }
        SlideType.VIDEO -> {
            val playVideo = rememberSaveable {
                mutableStateOf(false)
            }
            LaunchedEffect(image) {
                delay(1500L)
                playVideo.value = true
            }
            if(playVideo.value) {
                VideoPlayerView(
                    modifier = modifier.clip(shape).background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = shape
                    ),
                    url = image,
                    playerConfig = PlayerConfig(
                        isSpeedControlEnabled = false,
                        isScreenResizeEnabled = false,
                        isFullScreenEnabled = false,
                        isSeekBarVisible = false,
                        isScreenLockEnabled = false,
                        isDurationVisible = false,
                        isAutoHideControlEnabled = false,
                        isFastForwardBackwardEnabled = false,
                        isPauseResumeEnabled = false,
                        reelVerticalScrolling = true
                    )
                )
            } else {
                SkeletonRounded(Modifier.fillMaxWidth().height(140.dp), borderRadius = 10.dp)
            }
        }
    }
}
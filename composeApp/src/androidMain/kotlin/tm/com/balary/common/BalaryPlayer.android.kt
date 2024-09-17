package tm.com.balary.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import chaintech.videoplayer.ui.video.VideoPlayerView


@Composable
actual fun BalaryVideoPlayer(
    modifier: Modifier,
    url: String,
) {
    VideoPlayerView(
        modifier = modifier,
        url = "https://www.w3schools.com/tags/mov_bbb.mp4"
    )
}
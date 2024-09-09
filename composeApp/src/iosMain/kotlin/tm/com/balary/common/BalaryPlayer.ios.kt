package tm.com.balary.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.play
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL
import platform.Foundation.setValue
import platform.UIKit.UIDevice
import platform.UIKit.UIInterfaceOrientation




@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun BalaryVideoPlayer(modifier: Modifier, url: String) {
    val playerView = remember {
        AVPlayer(uRL = NSURL(string = url))
    }

    val playerController = AVPlayerViewController()
    playerController.player = playerView

    playerView.play()

    UIKitView(
        modifier = modifier,
        factory = {
            playerController.view
        },
        update = {

        }
    )

}
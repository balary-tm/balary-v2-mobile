package tm.com.balary.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.play
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL
import platform.Foundation.setValue
import platform.UIKit.UIDevice
import platform.UIKit.UIInterfaceOrientation




@Composable
actual fun BalaryVideoPlayer(modifier: Modifier, url: String) {
    val playerView = remember {
        AVPlayer(uRL = NSURL(string = url))
    }

    val playerController = AVPlayerViewController()
    playerController.player = playerView

    playerView.play()

    UIKitView(
        factory = {
            playerController.view
        },
        update = {

        }
    )

}
package tm.com.balary.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.play
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL
import platform.AVFoundation.AVPlayerLayer
import platform.UIKit.UIView
import platform.QuartzCore.CATransaction
import platform.QuartzCore.kCATransactionDisableActions
import platform.CoreGraphics.CGRect
import kotlinx.cinterop.CValue
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGRectZero


@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun BalaryVideoPlayer(modifier: Modifier, url: String) {
    val player = remember { AVPlayer(uRL = NSURL.URLWithString(url)!!) }
    val playerLayer = remember { AVPlayerLayer() }
    val avPlayerViewController = remember { AVPlayerViewController() }
    avPlayerViewController.player = player
    avPlayerViewController.showsPlaybackControls = true

    playerLayer.player = player
    // Use a UIKitView to integrate with your existing UIKit views
    UIKitView(
        factory = {
            // Create a UIView to hold the AVPlayerLayer
            val playerContainer = UIView()
            playerContainer.addSubview(avPlayerViewController.view)
            // Return the playerContainer as the root UIView
            playerContainer
        },
        update = { view ->
            val frame = view.superview?.bounds ?: CGRectZero
            CATransaction.begin()
            CATransaction.setValue(true, kCATransactionDisableActions)
            view.layer.frame = frame as CValue<CGRect>
            playerLayer.frame = frame
            avPlayerViewController.view.layer.frame = frame
            CATransaction.commit()

            player.play()
            avPlayerViewController.player!!.play()
        },
        modifier = modifier)

}
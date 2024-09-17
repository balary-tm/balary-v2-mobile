
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.window.ComposeUIViewController
//import platform.Foundation.fileURLWithPathComponents
//import platform.Foundation.lastPathComponent
//import platform.Foundation.pathComponents

@OptIn(ExperimentalComposeApi::class)
fun MainViewController() = ComposeUIViewController(
    configure = {
        enforceStrictPlistSanityCheck = false
        platformLayers = true
    }
) { App() }
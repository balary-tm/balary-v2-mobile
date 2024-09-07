package tm.com.balary.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun BalaryVideoPlayer(
    modifier: Modifier,
    url: String
)
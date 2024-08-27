package tm.com.balary.features.ads.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.close_filled
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.ui.ImageLoader

@Composable
fun PopupAds(
    show: Boolean = false,
    onClose: () -> Unit
) {
    if(show) {
        Dialog(
            onDismissRequest = onClose,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(Modifier.fillMaxSize()) {
                IconButton(
                    modifier = Modifier.align(Alignment.TopEnd).offset(y = 28.dp, x = (-8).dp),
                    onClick = {
                        onClose()
                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.close_filled),
                        contentDescription = "close",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
                ImageLoader(
                    Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.8f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable {

                        },
                    url = "",
                    placeholder = painterResource(Res.drawable.banner),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}
package tm.com.balary.features.product.presentation.ui.photo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.category
import balary.composeapp.generated.resources.close_filled
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
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
fun PhotoViewDialog(modifier: Modifier = Modifier, show: Boolean = false, onClose: () -> Unit) {
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
            PhotoView(Modifier.fillMaxSize(), onClose = onClose)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoView(modifier: Modifier = Modifier, onClose: () -> Unit = {}) {
    val pagerState = rememberPagerState { 10 }
    Box(modifier) {
        HorizontalPager(
            pagerState, contentPadding = PaddingValues(
                horizontal = 16.dp
            ),
            pageSpacing = 12.dp
        ) {
            ImageLoader(
                modifier = Modifier.fillMaxSize().zoomable(rememberZoomState()),
                url = "",
                placeholder = painterResource(Res.drawable.banner)
            )
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
            count = 10,
            current = pagerState.currentPage
        )
    }
}
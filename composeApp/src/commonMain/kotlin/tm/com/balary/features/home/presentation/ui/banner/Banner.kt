package tm.com.balary.features.home.presentation.ui.banner

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.logo_text
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.product.presentation.ui.detail.Indicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Banner(modifier: Modifier = Modifier) {
    val pager = rememberPagerState { 4 }
    Box(modifier) {
        HorizontalPager(
            pager,
            modifier = Modifier.fillMaxWidth(),
            pageSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { index ->
            BannerItem(Modifier.fillMaxWidth())
        }
        Indicator(
            modifier = Modifier.align(Alignment.BottomCenter).offset(
                y = (-8).dp
            ),
            count = 4,
            current = pager.currentPage
        )
    }
}

@Composable
fun BannerItem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier.clip(RoundedCornerShape(12.dp)).height(140.dp).background(
            color = MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(12.dp)
        )
    ) {
        Image(
            painter = painterResource(Res.drawable.banner),
            contentDescription = "banner",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}
package tm.com.balary.features.home.presentation.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import tm.com.balary.features.ads.presentation.ui.PopupAds
import tm.com.balary.features.ads.presentation.ui.SheetAds
import tm.com.balary.features.home.presentation.ui.banner.Banner
import tm.com.balary.features.home.presentation.ui.banner.HomeToolbar
import tm.com.balary.features.home.presentation.ui.banner.SearchInput
import tm.com.balary.features.home.presentation.ui.brand.Brands
import tm.com.balary.features.home.presentation.ui.category.HomeCategory
import tm.com.balary.features.home.presentation.ui.product.HomeSection

class HomeScreen : Screen {
    @Composable
    override fun Content() {
    }

}

@Composable
fun Home(navHostController: NavHostController) {

    val lazyScroll = rememberLazyListState()

    val showPopup = rememberSaveable {
        mutableStateOf(true)
    }

    val showSheetAds = rememberSaveable {
        mutableStateOf(false)
    }

    val strings = LocalStrings.current

    PopupAds(
        show = showPopup.value,
        onClose = {
            showSheetAds.value = true
            showPopup.value = false
        }
    )

    SheetAds(
        show = showSheetAds.value,
        onClose = {
            showSheetAds.value = false
        }
    )



    val animatedOffset = animateDpAsState(if(lazyScroll.firstVisibleItemIndex > 0) 0.dp else (-100).dp)



    Box(
        modifier = Modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.background
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().zIndex(2f).offset(y = animatedOffset.value).background(Color.Transparent).background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)
            ).clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)).padding(16.dp)
        ) {
            SearchInput(
                modifier = Modifier.fillMaxWidth(),
                onSearch = { query->

                }
            )
        }
        LazyColumn (
            Modifier.fillMaxWidth(),
            state = lazyScroll,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                HomeToolbar(modifier = Modifier.fillMaxWidth())
            }
            item {
                Column(
                    Modifier.fillMaxWidth().background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                    )
                ) {
                    Spacer(Modifier.height(8.dp))
                    Banner(Modifier.fillMaxWidth())
                    HomeCategory(Modifier.fillMaxWidth())
                    Spacer(Modifier.height(8.dp))
                }
            }
            items(20) { index ->
                HomeSection(
                    Modifier.fillMaxWidth(),
                    title = strings.discountProducts,
                    adsCount = if (index > 0) 1 else 2,
                    navHostController = navHostController
                )
            }
            item {
                Brands(Modifier.fillMaxWidth())
            }
            item {
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
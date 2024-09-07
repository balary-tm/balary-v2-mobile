package tm.com.balary.features.product.presentation.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.home.presentation.ui.banner.SearchInput
import tm.com.balary.features.home.presentation.ui.product.HomeSection
import tm.com.balary.features.product.presentation.ui.FavoriteButton
import tm.com.balary.features.product.presentation.ui.ProductBasketButton
import tm.com.balary.features.product.presentation.ui.photo.PhotoViewDialog
import tm.com.balary.features.product.presentation.ui.photo.PhotoViewScreen
import tm.com.balary.features.product.presentation.ui.review.MiniReview
import tm.com.balary.features.product.presentation.ui.review.ProductReviewScreen
import tm.com.balary.ui.ImageLoader

class ProductDetailScreen : Screen {
    @Composable
    override fun Content() {
        ProductDetail(Modifier.fillMaxSize())
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductDetail(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.currentOrThrow
    val imageCount = 10
    val showPhotoView = remember {
        mutableStateOf(false)
    }
    val pagerState = rememberPagerState { imageCount }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()



    PhotoViewDialog(
        show = showPhotoView.value,
        onClose = {
            showPhotoView.value = false
        }
    )
    Column(
        modifier.background(
            color = MaterialTheme.colorScheme.background
        )
    ) {
        Scaffold(Modifier.weight(1f).nestedScroll(scrollBehavior.nestedScrollConnection),
            backgroundColor = MaterialTheme.colorScheme.background, topBar = {
            CenterAlignedTopAppBar(
                actions = {
                    IconButton(
                        onClick = {
                        }
                    ) {
                        Icon(
                            Icons.Default.IosShare,
                            contentDescription = "share",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigator.pop()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Default.KeyboardArrowLeft,
                            contentDescription = "back",
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth(),
                title = {
                    SearchInput(
                        modifier = Modifier.fillMaxWidth(),
                        onSearch = { query ->

                        }
                    )
                }
            )
        }) {
            Column(
                Modifier.fillMaxWidth().verticalScroll(
                    rememberScrollState()
                )
            ) {
                Box(
                    Modifier.fillMaxWidth().height(340.dp).background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(
                            bottomEnd = 20.dp,
                            bottomStart = 20.dp
                        )
                    ).padding(
                        vertical = 12.dp
                    )
                ) {

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxSize().padding(bottom = 28.dp),
                        contentPadding = PaddingValues(
                            horizontal = 16.dp
                        ),
                        pageSpacing = 12.dp
                    ) {
                        ImageLoader(
                            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(20.dp)).clickable {
                                showPhotoView.value = true
                            },
                            url = "",
                            placeholder = painterResource(Res.drawable.banner),
                            contentScale = ContentScale.Crop
                        )

                    }

                    Box(
                        Modifier.fillMaxSize().padding(
                            horizontal = 36.dp,
                            vertical = 12.dp
                        )
                    ) {
                        FavoriteButton(
                            modifier = Modifier.size(30.dp)
                        )

                        Text(
                            "Täze haryt",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700,
                                fontSize = 18.sp
                            ),
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(20.dp)
                            ).padding(4.dp).align(
                                Alignment.TopEnd
                            )
                        )

                        Text(
                            "-10%",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.onError,
                            modifier = Modifier.padding(bottom = 30.dp).background(
                                color = MaterialTheme.colorScheme.error,
                                shape = RoundedCornerShape(20.dp)
                            ).padding(4.dp).align(
                                Alignment.BottomStart
                            )
                        )
                    }

                    Indicator(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        count = imageCount,
                        current = pagerState.currentPage
                    )

                }

                Spacer(Modifier.height(8.dp))

                Column(
                    Modifier.fillMaxWidth().background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp
                        )
                    ).padding(vertical = 20.dp)
                ) {


                    Column(
                        Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            "Gyzyl alma Turk 1 kg",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W900,
                                fontSize = 18.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(Modifier.height(4.dp))
                        ProductDetailPrice(Modifier.fillMaxWidth())

                        ProductInfo(
                            modifier = Modifier.fillMaxWidth(),
                            title = "Bar kody:",
                            value = "ABC-abc-1234"
                        )

                        ProductInfo(
                            modifier = Modifier.fillMaxWidth(),
                            title = "Harydyň kody:",
                            value = "34"
                        )

                        ProductInfo(
                            modifier = Modifier.fillMaxWidth(),
                            titleSize = 16.sp,
                            title = "Giňişleýin maglumat we peýdasy:",
                            value = "Içi dykyz, suwly, çala turşuja, ýakymly tagamly. Gyzyl almalar kiçiräk sowuga çydamly, güýzki sorta deişli. Olar galyňlygy ýeterlikli goýy gyzyl owüsgünli gabyga eýe bolýarlar."
                        )

                        ProductInfo(
                            modifier = Modifier.fillMaxWidth(),
                            title = "Peýdasy:",
                            value = "Almalar günüň dowamynda ýeňil hem-de peýdaly naharlanmak üçin laýyk gelýärler. "
                        )

                        ProductInfo(
                            modifier = Modifier.fillMaxWidth(),
                            title = "Möhleti:",
                            value = "30 gün "
                        )

                        ProductInfo(
                            modifier = Modifier.fillMaxWidth(),
                            title = "Saklanyş şerti:",
                            value = "+2°C...+8°C"
                        )

                        ProductInfo(
                            modifier = Modifier.fillMaxWidth(),
                            title = "Öndürilen ýurdy:",
                            value = "Türkiýe"
                        )

                        Text(
                            "Rating & Reviews",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W900
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.clickable {
                                navigator.push(ProductReviewScreen())
                            }
                        )

                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                Modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                RatingBar(Modifier, 4.0, 21.dp)
                                Text(
                                    "4/5",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.W700
                                    ),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.background(
                                        color = Color(0xFF2D98FF),
                                        shape = RoundedCornerShape(4.dp)
                                    ).padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }


                            Button(
                                onClick = {

                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF614FE0)
                                ),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    "Teswir goýmak",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.W900
                                    )
                                )
                            }

                        }


                    }

                    Spacer(Modifier.height(16.dp))

                    LazyRow(
                        contentPadding = PaddingValues(
                            horizontal = 16.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(20) {
                            MiniReview(
                                Modifier.width(300.dp),
                                username = "Jahan",
                                stars = 3.0,
                                image = "",
                                date = "08.06.2024",
                                review = "Öran gowy haryt."
                            )
                        }
                    }


                }

                Spacer(Modifier.height(8.dp))

                HomeSection(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Meňzeş harytlar",
                    adsCount = 0,
                    showBanner = false
                )
                Spacer(Modifier.height(8.dp))
            }
        }




        Spacer(Modifier.height(8.dp))

        Column(
            Modifier.fillMaxWidth().background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            ).padding(16.dp)
        ) {
            ProductBasketButton(
                modifier = Modifier.fillMaxWidth().height(44.dp),
                initialCount = 0,
                bigButton = true
            )
        }

        Spacer(Modifier.height(8.dp))




    }
}
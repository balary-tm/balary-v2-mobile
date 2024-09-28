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
import androidx.compose.material.LinearProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.NavHostController
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.placeholder
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import chaintech.videoplayer.model.PlayerConfig
import chaintech.videoplayer.ui.video.VideoPlayerView
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import tm.com.balary.features.home.presentation.ui.banner.SearchInput
import tm.com.balary.features.home.presentation.ui.product.HomeSection
import tm.com.balary.features.product.presentation.ui.FavoriteButton
import tm.com.balary.features.product.presentation.ui.ProductBasketButton
import tm.com.balary.features.product.presentation.ui.photo.PhotoViewDialog
import tm.com.balary.features.product.presentation.ui.review.CommentForm
import tm.com.balary.features.product.presentation.ui.review.MiniReview
import tm.com.balary.features.product.presentation.ui.review.ProductReviewScreen
import tm.com.balary.features.product.presentation.viewmodel.ProductViewModel
import tm.com.balary.locale.translateValue
import tm.com.balary.router.ProductDetailScreen
import tm.com.balary.ui.AppError
import tm.com.balary.ui.ImageLoader

class ProductDetailScreen : Screen {
    @Composable
    override fun Content() {
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductDetail(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    productId: String
) {

    val productViewModel: ProductViewModel = koinViewModel()
    val productState = productViewModel.singleProductState.collectAsState()

    LaunchedEffect(true) {
        productViewModel.initSingleProduct(productId)
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()



    Column(
        modifier.background(
            color = MaterialTheme.colorScheme.background
        )
    ) {
        Scaffold(Modifier.weight(1f).nestedScroll(scrollBehavior.nestedScrollConnection),
            backgroundColor = MaterialTheme.colorScheme.background,
            topBar = {
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
                                navHostController.navigateUp()
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
            if(productState.value.loading) {
                ProductDetailSkeleton(Modifier.fillMaxSize())
            } else if (productState.value.error.isNullOrEmpty().not()) {
                AppError(
                    Modifier.fillMaxSize()
                )
            } else {
                productState.value.product?.let { product->

                    val imageCount = product.medias?.count()?:0
                    val showPhotoView = remember {
                        mutableStateOf(false)
                    }
                    val pagerState = rememberPagerState { imageCount }


                    val addComment = remember {
                        mutableStateOf(false)
                    }

                    CommentForm(
                        show = addComment.value,
                        onDismiss = {
                            addComment.value = false
                        }
                    )





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

                            product.medias?.let { images->
                                PhotoViewDialog(
                                    show = showPhotoView.value,
                                    images = images,
                                    initialIndex = pagerState.currentPage,
                                    onClose = {
                                        showPhotoView.value = false
                                    }
                                )
                                HorizontalPager(
                                    state = pagerState,
                                    modifier = Modifier.fillMaxSize().padding(bottom = 28.dp),
                                    contentPadding = PaddingValues(
                                        horizontal = 16.dp
                                    ),
                                    pageSpacing = 12.dp
                                ) { index->
                                    val media = images[index]
                                    if(media.type == "video") {
                                        VideoPlayerView(
                                            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(20.dp))
                                                .clickable {
                                                    showPhotoView.value = true
                                                },
                                            url = media.media_path,
                                            playerConfig = PlayerConfig(
                                                isPause = true,
                                                isSpeedControlEnabled = false,
                                                isScreenResizeEnabled = false,
                                                isFullScreenEnabled = false,
                                                isSeekBarVisible = true,
                                                isScreenLockEnabled = false,
                                                isDurationVisible = false,
                                                isAutoHideControlEnabled = false,
                                                isFastForwardBackwardEnabled = false,
                                                isPauseResumeEnabled = true,
                                                reelVerticalScrolling = false
                                            )
                                        )
                                    } else {
                                        ImageLoader(
                                            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(20.dp))
                                                .clickable {
                                                    showPhotoView.value = true
                                                },
                                            url = images[index].media_path,
                                            placeholder = painterResource(Res.drawable.placeholder),
                                            contentScale = ContentScale.Crop
                                        )
                                    }

                                }
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

                                product.label?.let { label->
                                    Text(
                                        product.prettyLabel(),
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
                                }

                               product.discount?.let {
                                   if(product.discount>0) {
                                       Text(
                                           "-${product.discount}%",
                                           style = MaterialTheme.typography.bodyMedium.copy(
                                               fontWeight = FontWeight.W700
                                           ),
                                           color = Color.White,
                                           modifier = Modifier.padding(bottom = 30.dp).background(
                                               color = MaterialTheme.colorScheme.error,
                                               shape = RoundedCornerShape(20.dp)
                                           ).padding(4.dp).align(
                                               Alignment.BottomStart
                                           )
                                       )
                                   }
                               }
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
                                    translateValue(product,"title"),
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.W900,
                                        fontSize = 18.sp
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(Modifier.height(4.dp))
                                ProductDetailPrice(
                                    Modifier.fillMaxWidth(),
                                    price = product.price,
                                    discountPrice = product.discount_price,
                                    discount = product.discount
                                )

                                product.code?.let { it1 ->
                                    ProductInfo(
                                        modifier = Modifier.fillMaxWidth(),
                                        title = strings.barcode,
                                        value = it1
                                    )
                                }

                                product.sku?.let { it1 ->
                                    ProductInfo(
                                        modifier = Modifier.fillMaxWidth(),
                                        title = strings.productNumber,
                                        value = it1
                                    )
                                }

                                ProductInfo(
                                    modifier = Modifier.fillMaxWidth(),
                                    titleSize = 16.sp,
                                    title = strings.productBenefit,
                                    value = translateValue(product, "description")
                                )

                                product.properties?.let { properties ->
                                    repeat(properties.count()) {index->
                                        val property = properties[index]
                                        val value = property.toRealObject()
                                        val real = if(value.first!=null) {
                                            translateValue(value.first, "", prefix = "")
                                        } else
                                            value.second.toString()
                                        ProductInfo(
                                            modifier = Modifier.fillMaxWidth(),
                                            title = translateValue(property.property, "", prefix = ""),
                                            value = real
                                        )
                                    }
                                }

                                product.other_variants?.let { variants->
                                    repeat(variants.count()) {index->
                                        val variant = variants[index]
                                        ProductVariant(
                                            item = variant,
                                            currentProductId = productId,
                                            onClickVariant = { pid->
                                                navHostController.navigate(ProductDetailScreen(pid))
                                            }
                                        )
                                    }
                                }

                                Text(
                                    strings.ratingsAndReviews,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.W900
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.clickable {
                                        navHostController.navigate(tm.com.balary.router.ProductReviewScreen)
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
                                            addComment.value = true
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFF614FE0)
                                        ),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            strings.putComment,
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

                        product.similar_products?.data?.let { similar->
                            if(similar.isNotEmpty()) {
                                HomeSection(
                                    modifier = Modifier.fillMaxWidth(),
                                    title = strings.similarProducts,
                                    adsCount = 0,
                                    navHostController = navHostController,
                                    products = similar.map { it.toUIEntity() }
                                )
                                Spacer(Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }


        productState.value.product?.let {
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
                    bigButton = true,
                    onCountChange = {

                    }
                )
            }

            Spacer(Modifier.height(8.dp))
        }





    }
}
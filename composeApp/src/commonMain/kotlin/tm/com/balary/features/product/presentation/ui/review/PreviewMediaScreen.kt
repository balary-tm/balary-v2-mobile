package tm.com.balary.features.product.presentation.ui.review

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.add_basket
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.close_filled
import balary.composeapp.generated.resources.fullscreen
import balary.composeapp.generated.resources.like
import balary.composeapp.generated.resources.message
import balary.composeapp.generated.resources.mute
import chaintech.videoplayer.ui.video.VideoPlayerView
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.product.presentation.ui.photo.rememberZoomState
import tm.com.balary.features.product.presentation.ui.photo.zoomable
import tm.com.balary.ui.ImageLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewMediaScreen(
    show: Boolean,
    onDismiss: () -> Unit
) {
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (show) {
        ModalBottomSheet(
            sheetState = state,
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 0.dp,
            onDismissRequest = {
                onDismiss()
            },
            shape = RoundedCornerShape(0.dp)
        ) {
            MediaContainer(Modifier.fillMaxSize(), onDismiss)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MediaContainer(modifier: Modifier = Modifier, onClose: () -> Unit) {
    val pagerState = rememberPagerState { 6 }
    Box(
        modifier.background(
            color = Color.Black
        )
    ) {
//        VerticalPager(
//            state = pagerState
//        ) { index ->
//            MediaPreview(Modifier.fillMaxSize(), isVideo = index % 2 == 0)
//        }

        VideoPlayerView(
            modifier = Modifier.fillMaxSize(),
            url = "https://www.w3schools.com/tags/mov_bbb.mp4"
        )

        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ImageLoader(
                    modifier = Modifier.size(32.dp).clip(CircleShape).border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.surface,
                        shape = CircleShape
                    ).shadow(1.dp, shape = CircleShape),
                    url = "",
                    placeholder = painterResource(Res.drawable.banner),
                    contentScale = ContentScale.Crop
                )

                Text(
                    "Jahan",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W700,
                        fontSize = 18.sp
                    ),
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

            }

            IconButton(
                modifier = Modifier,
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
        }

        Column(
            Modifier.align(Alignment.CenterEnd).padding(end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.like),
                contentDescription = "like",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(26.dp).clip(CircleShape).clickable {

                }.padding(2.dp)
            )
            Text(
                "1",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700
                ),
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(Modifier.width(2.dp))

            Icon(
                painter = painterResource(Res.drawable.message),
                contentDescription = "message",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(26.dp).clip(CircleShape).clickable {

                }.padding(2.dp)
            )
            Text(
                "1",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700
                ),
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(Modifier.width(2.dp))

            Icon(
                painter = painterResource(Res.drawable.mute),
                contentDescription = "message",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(26.dp).clip(CircleShape).clickable {

                }.padding(2.dp)
            )
        }

        Column(
            Modifier.fillMaxWidth().align(
                alignment = Alignment.BottomStart
            ).padding(16.dp).padding(bottom = 40.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "rating",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    "5 • Öran gowy haryt.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700
                    ),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(Modifier.height(22.dp))

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                ImageLoader(
                    modifier = Modifier.size(60.dp).clip(RoundedCornerShape(10.dp)).background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(10.dp)
                    ),
                    url = ""
                )

                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            "175,00m.",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        Text(
                            "175,00 m.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.error,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }

                    Text(
                        "Lorem ipsum dolor sit amet, consectetu Lorem ipsum dolor sit amet, consectetu",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W700
                        ),
                        color = MaterialTheme.colorScheme.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                IconButton(
                    onClick = {

                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.size(48.dp).clip(CircleShape).padding(4.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.add_basket),
                        contentDescription = "add basket",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }



            }

            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = 0.3f,
                onValueChange = {

                },
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.White
                )
            )

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    Icons.Default.PauseCircle,
                    contentDescription = "play or pause",
                    tint = Color.White,
                    modifier = Modifier.size(26.dp).clip(CircleShape).clickable {

                    }.padding(2.dp)
                )

                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(
                            color = Color.White
                        )) {
                            append("00:05")
                        }

                        withStyle(
                            SpanStyle(
                            color = MaterialTheme.colorScheme.outline
                        )
                        ) {
                            append(" / 00:19")
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W700
                    )
                )

                Icon(
                    painter = painterResource(Res.drawable.fullscreen),
                    contentDescription = "fullscreen",
                    tint = Color.White,
                    modifier = Modifier.size(26.dp).clip(CircleShape).clickable {

                    }.padding(2.dp)
                )
            }
        }
    }
}

@Composable
fun MediaPreview(
    modifier: Modifier = Modifier,
    isVideo: Boolean = true
) {
    if (isVideo) {

//        BalaryPlayer(
//            modifier = Modifier.fillMaxSize(),
//            url = "https://www.w3schools.com/tags/mov_bbb.mp4"
//        )
    } else {
        ImageLoader(
            modifier = modifier.fillMaxSize().zoomable(rememberZoomState()),
            url = "",
            placeholder = painterResource(Res.drawable.banner)
        )
    }
}
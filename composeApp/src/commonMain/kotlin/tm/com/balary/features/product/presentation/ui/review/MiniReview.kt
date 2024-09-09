package tm.com.balary.features.product.presentation.ui.review

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.dislike
import balary.composeapp.generated.resources.edit
import balary.composeapp.generated.resources.like
import balary.composeapp.generated.resources.play
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.product.presentation.ui.detail.RatingBar
import tm.com.balary.ui.ImageLoader

@Composable
fun MiniReview(
    modifier: Modifier = Modifier,
    image: String,
    username: String,
    stars: Double,
    date: String,
    review: String,
    isOwn: Boolean = false
) {
    val show = remember {
        mutableStateOf(false)
    }
    val editShow = remember {
        mutableStateOf(false)
    }


    val navigator = LocalNavigator.currentOrThrow

    CommentForm(
        show = editShow.value,
        onDismiss = {
            editShow.value = false
        }
    )

    PreviewMediaScreen(
        show = show.value,
        onDismiss = {
            show.value = false
        }
    )
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceBright,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable {

            }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            ImageLoader(
                modifier = Modifier.size(32.dp).clip(CircleShape).border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.surface,
                    shape = CircleShape
                ).shadow(1.dp, shape = CircleShape),
                url = image,
                placeholder = painterResource(Res.drawable.banner),
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    username,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W700
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                RatingBar(
                    stars = stars,
                    starSize = 12.dp,
                    spacing = 1.5.dp
                )


            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.End
            ) {

                if (isOwn) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        shape = RoundedCornerShape(4.dp),
                        onClick = {
                            editShow.value = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.edit),
                            contentDescription = "edit",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            "Düzetmek",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        repeat(2) {
                            ReviewImage(
                                isVideo = it % 2 == 0,
                                onClick = {
                                    show.value = true
                                }
                            )
                        }
                    }
                }

                Text(
                    date,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.W700
                    ),
                    color = MaterialTheme.colorScheme.outline,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }


        }





        Text(
            review,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        if (isOwn) {
            LazyRow(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(8) {
                    ReviewImage(
                        modifier = Modifier,
                        imageSize = 100.dp
                    )
                }
            }
        } else {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    painter = painterResource(Res.drawable.like),
                    contentDescription = "like",
                    tint = Color(0xFF614FE0),
                    modifier = Modifier.size(26.dp).clip(CircleShape).clickable {

                    }.padding(2.dp)
                )
                Spacer(Modifier.width(2.dp))
                Text(
                    "1",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.width(2.dp))
                Icon(
                    painter = painterResource(Res.drawable.dislike),
                    contentDescription = "dislike",
                    tint = Color(0xFF614FE0),
                    modifier = Modifier.size(26.dp).clip(CircleShape).clickable {

                    }.padding(2.dp)
                )
                Spacer(Modifier.width(2.dp))
                Text(
                    "0",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }

    }
}

@Composable
fun ReviewImage(
    modifier: Modifier = Modifier,
    isVideo: Boolean = false,
    imageSize: Dp = 36.dp,
    onClick: () -> Unit = {}
) {
    val shape = RoundedCornerShape(4.dp)
    Box(
        modifier = modifier.clip(shape).border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurface,
            shape = shape
        ).clickable {
            onClick()
        }, contentAlignment = Alignment.Center
    ) {
        ImageLoader(
            modifier = Modifier.size(imageSize).clip(shape),
            url = "",
            contentScale = ContentScale.Fit
        )

        if (isVideo) {
            Icon(
                painter = painterResource(Res.drawable.play),
                contentDescription = "play",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
package tm.com.balary.features.product.presentation.ui.review

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.comment
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.auth.presentation.ui.BackScreen
import tm.com.balary.features.order.presentation.ui.OrderedProducts
import tm.com.balary.features.product.presentation.ui.detail.RatingBar
import tm.com.balary.ui.AlsFontFamily
import kotlin.random.Random

class ProductReviewScreen : Screen {
    @Composable
    override fun Content() {
        val showWarning = remember {
            mutableStateOf(false)
        }

        val navigator = LocalNavigator.currentOrThrow

        PreviewWarning(
            show = showWarning.value,
            onDismiss = {
                showWarning.value = false
            }
        )

        BackScreen(Modifier.fillMaxSize(), title = "Teswirler", actions = {
            Box(
                Modifier.background(
                    color = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.6f
                    ),
                    shape = RoundedCornerShape(4.dp)
                ).clip(RoundedCornerShape(4.dp)).clickable {
                    navigator.push(OrderedProducts())
                }.padding(vertical = 10.dp, horizontal = 4.dp)
            ) {
                Text(
                    "Meniň teswirlerim",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W700
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }) {
            Box(Modifier.fillMaxSize()) {
                LazyColumn(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(
                        bottom = 12.dp
                    )
                ) {
                    item {
                        ReviewChart(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    item {
                        Text(
                            "8 Teswir",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W700
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                    items(86) {
                        MiniReview(
                            modifier = Modifier.fillMaxWidth().padding(
                                horizontal = 16.dp
                            ),
                            username = "Jahan",
                            stars = 3.0,
                            image = "",
                            date = "08.06.2024",
                            review = "Öran gowy haryt."
                        )
                    }
                }

                Column(
                    Modifier.align(Alignment.BottomEnd).padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowUp,
                        contentDescription = "up",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(40.dp).clip(CircleShape).shadow(2.dp).background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = CircleShape
                        ).clickable {

                        }.padding(2.dp)
                    )

                    Button(
                        onClick = {
                            showWarning.value = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF614FE0)
                        ),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.comment),
                            contentDescription = "comment",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(Modifier.width(2.dp))
                        androidx.compose.material3.Text(
                            "Teswir goýmak",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.W900
                            )
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun ReviewChart(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(20.dp)
        ).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontFamily = AlsFontFamily(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W900
                )
            ) {
                append("4.6")
            }
            withStyle(
                SpanStyle(
                    fontFamily = AlsFontFamily(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700
                )
            ) {
                append("/5")
            }
        }

        Column {
            Text(
                text,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(9.dp))
            Text(
                "86 Teswir",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        VerticalDivider(
            thickness = 1.dp,
            modifier = Modifier.defaultMinSize(
                minHeight = 110.dp
            )
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(40f)) {
            repeat(5) { index ->
                RatingBar(
                    stars = (index + 1).toDouble(),
                    starSize = 14.dp,
                    spacing = 5.dp
                )
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(60f)) {
            repeat(5) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    LinearProgressIndicator(
                        progress = "0.${it + 1}".toFloat(),
                        Modifier.weight(1f),
                        color = Color(0xFFFFC120)
                    )

                    Text(
                        "$it",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.W700
                        )
                    )
                }
            }
        }


    }
}
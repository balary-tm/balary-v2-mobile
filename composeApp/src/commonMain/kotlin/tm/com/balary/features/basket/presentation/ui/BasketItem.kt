package tm.com.balary.features.basket.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.close_filled
import cafe.adriel.lyricist.LocalStrings
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.product.presentation.ui.ProductBasketButton
import tm.com.balary.ui.AppAlert
import tm.com.balary.ui.AppAlertType
import tm.com.balary.ui.ImageLoader

@Composable
fun BasketItem(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val show = remember {
        mutableStateOf(false)
    }

    val strings = LocalStrings.current

    AppAlert(
        show = show.value,
        onDismiss = {
            show.value = false
        },
        title = strings.confirmation,
        message = buildAnnotatedString {
            append(strings.removeFromBasket)
        },
        type = AppAlertType.DANGER
    )
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ImageLoader(
            modifier = Modifier.width(115.dp).height(150.dp).shadow(8.dp, RoundedCornerShape(10.dp)).clip(
                RoundedCornerShape(10.dp)
            ).background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(10.dp)
            ),
            url = "",
            contentScale = ContentScale.Inside
        )

        Column(Modifier.weight(1f)) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Product name",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W900
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                IconButton(
                    onClick = {
                        show.value = true
                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.close_filled),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "close"
                    )
                }
            }

            Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )

            Spacer(Modifier.height(8.dp))

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProductBasketButton(
                    modifier = Modifier.width(120.dp).height(40.dp)
                )

                Text(
                    "175,00 m.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W900
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}
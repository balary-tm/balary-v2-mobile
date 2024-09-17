package tm.com.balary.features.order.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.call
import balary.composeapp.generated.resources.comment
import balary.composeapp.generated.resources.home
import balary.composeapp.generated.resources.info_fill
import balary.composeapp.generated.resources.paper
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.auth.presentation.ui.BackScreen

@Composable
fun OrderDetails(navHostController: NavHostController) {
    val strings = LocalStrings.current
    BackScreen(Modifier.fillMaxSize(), title = "${strings.orderNumber}: #26491", actions = {
        Box(
            Modifier.background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                shape = RoundedCornerShape(4.dp)
            ).clip(RoundedCornerShape(4.dp)).padding(vertical = 4.dp, horizontal = 15.dp)
        ) {
            androidx.compose.material.Text(
                strings.delivered,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W700
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }, navHostController = navHostController) {
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                Modifier.fillMaxWidth().background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                ).padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .shadow(1.dp)
                        .background(MaterialTheme.colorScheme.surface, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.home),
                        contentDescription = "order_address",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        "03 Aprel 2024 12:10",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        "Mir 7/4 42-jaý, 12 öý",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Image(
                    painter = painterResource(Res.drawable.paper),
                    contentDescription = "paper",
                    modifier = Modifier.size(36.dp)
                )
            }

            Text(
                strings.products,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W700,
                    fontSize = 18.sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            repeat(20) {
                OrderDetailItem(Modifier.fillMaxWidth().padding(horizontal = 16.dp))
            }

            Row(
                Modifier.fillMaxWidth().padding(
                    horizontal = 16.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.info_fill),
                    contentDescription = "info",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(20.dp)
                )

                Text(
                    strings.orderChangeInfo,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700
                    )
                )
            }

            Row(
                Modifier.fillMaxWidth().padding(
                    horizontal = 16.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.comment),
                        contentDescription = "chat",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        strings.chat,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Button(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.call),
                        contentDescription = "call",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        strings.call,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Text(
                strings.info,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W700,
                    fontSize = 18.sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Column(
                modifier = Modifier.fillMaxWidth().background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                ).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DetailsInfo(
                    title = strings.orderHistory,
                    value = "03 Aprel 2024 12:10"
                )

                DetailsInfo(
                    title = strings.courier,
                    value = "Nazar Nazarow"
                )

                DetailsInfo(
                    title = strings.products,
                    value = "28"
                )

                DetailsInfo(
                    title = strings.totalPrice,
                    value = "660.70 ${strings.money}"
                )

                DetailsInfo(
                    title = strings.productDiscount,
                    value = "-101.11 ${strings.money}"
                )

                DetailsInfo(
                    title = strings.delivery,
                    value = "15.00 ${strings.money}"
                )

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        strings.totalPayment,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        "558.59 ${strings.money}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End
                    )

                }

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        strings.paymentType,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ),
                        color = Color(0xFF614FE0),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        strings.cash,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ),
                        color = Color(0xFF614FE0),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End
                    )

                }


            }

            Spacer(Modifier.height(8.dp))
        }
    }
}


@Composable
fun DetailsInfo(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {

        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                value,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End
            )

        }

}
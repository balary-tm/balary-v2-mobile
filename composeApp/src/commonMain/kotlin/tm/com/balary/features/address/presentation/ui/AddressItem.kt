package tm.com.balary.features.address.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.delete
import balary.composeapp.generated.resources.home
import balary.composeapp.generated.resources.home_address
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tm.com.balary.ui.AppAlert
import tm.com.balary.ui.AppAlertType

@Composable
fun AddressItem(modifier: Modifier = Modifier) {
    val showDelete = remember {
        mutableStateOf(false)
    }
    AppAlert(
        show = showDelete.value,
        onDismiss = {
            showDelete.value = false
        },
        title = "Salgyny pozmak",
        message = buildAnnotatedString {
            append("Hakykatdan hem pozmak isleýärsiňizmi?")
        },
        type = AppAlertType.DANGER
    )
    Column(
        modifier = modifier.clip(RoundedCornerShape(4.dp)).border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
            shape = RoundedCornerShape(4.dp)
        )
    ) {
        Row(
            Modifier.fillMaxWidth().background(
                color = MaterialTheme.colorScheme.outline.copy(
                    alpha = 0.2f
                )
            ).padding(8.dp)
        ) {
            Text(
                "Öz salgym", style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W700
                ), color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                painter = painterResource(Res.drawable.delete),
                contentDescription = "delete",
                modifier = Modifier.size(20.dp).clip(CircleShape).clickable {
                    showDelete.value = true
                },
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(Modifier.height(8.dp))
        Text(
            "Mir 7/4 13 nji ýaşaýyş jaý 2 nji kw",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.W700
            ), color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(
                horizontal = 8.dp
            )
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier.background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(4.dp)
                ).clip(RoundedCornerShape(4.dp)).clickable {

                }.padding(
                    vertical = 4.dp,
                    horizontal = 12.dp
                )
            ) {
                Text(
                    "Kartadan görkezmek",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W700
                    ), color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.home_address),
                    contentDescription = "home",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.outline
                )
                Text(
                    "Öý salgym",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W700
                    ), color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Composable
@Preview
fun AddressPreie() {
    AddressItem()
}
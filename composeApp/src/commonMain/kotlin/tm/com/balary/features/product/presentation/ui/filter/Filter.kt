package tm.com.balary.features.product.presentation.ui.filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class FilterScreen : Screen {
    @Composable
    override fun Content() {
        Filter(Modifier.fillMaxSize())
    }
}

@Composable
fun Filter(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.currentOrThrow
    Column(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            Modifier.fillMaxWidth().background(
                color = MaterialTheme.colorScheme.surface
            ).padding(
                vertical = 12.dp,
                horizontal = 16.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement
                .spacedBy(8.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "back",
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.clip(CircleShape).clickable {
                    navigator.pop()
                }
            )

            Text(
                text = "Filterler",
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700
                )
            )
        }

        Column(
            Modifier.fillMaxWidth().weight(1f).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                Modifier.fillMaxWidth().background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(
                        bottomEnd = 20.dp,
                        bottomStart = 20.dp
                    )
                ).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Tertiplemek",
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700
                    )
                )

                CheckText(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Meşhur önümler",
                    checked = true
                )

                CheckText(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Täze önümler",
                    checked = false
                )

                CheckText(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Gymmatdan arzana",
                    checked = false
                )

                CheckText(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Arzandan gymmada",
                    checked = false
                )

            }

            Column(
                Modifier.fillMaxWidth().background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                ).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Bahasy",
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700
                    )
                )
                PriceRange(modifier = Modifier.fillMaxWidth())
            }



            RadioCollection(
                modifier = Modifier.fillMaxWidth(),
                title = "Kategorialar",
                items = mapOf(
                    "Miweler" to 1,
                    "Gök önümler" to 2,
                    "Bakja önümler" to 3,
                    "Egzotika miweler" to 4
                )
            )


            RadioCollection(
                modifier = Modifier.fillMaxWidth(),
                title = "Ölçeg birlikler",
                items = mapOf(
                    "100 gr" to 1,
                    "250 gr" to 2,
                    "500 gr" to 3,
                    "kg" to 4
                )
            )

            RadioCollection(
                modifier = Modifier.fillMaxWidth(),
                title = "Brendler",
                items = mapOf(
                    "Ýerli" to 1,
                    "Daşary ýurt" to 2,
                )
            )

            Spacer(Modifier.height(8.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth().background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            ).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                onClick = {

                },
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            ) {
                Text(
                    "Arassala",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700
                    )
                )
            }

            Button(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(1f),
                onClick = {

                }
            ) {
                Text(
                    "Ýatda sakla",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700
                    )
                )
            }
        }
    }
}
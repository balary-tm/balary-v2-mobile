package tm.com.balary.features.product.presentation.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProvider
import kotlinx.coroutines.launch
import tm.com.balary.features.product.data.entity.single.OtherVariantItem
import tm.com.balary.locale.translateValue

fun Color.Companion.fromHex(hex: String): Color {
    // Remove the leading '#' if it's there
    val cleanedHex = hex.removePrefix("#")

    // Parse the string into a color
    return when (cleanedHex.length) {
        6 -> {
            // RGB: #RRGGBB
            val r = cleanedHex.substring(0, 2).toInt(16)
            val g = cleanedHex.substring(2, 4).toInt(16)
            val b = cleanedHex.substring(4, 6).toInt(16)
            Color(r, g, b)
        }

        8 -> {
            // ARGB: #AARRGGBB
            val a = cleanedHex.substring(0, 2).toInt(16)
            val r = cleanedHex.substring(2, 4).toInt(16)
            val g = cleanedHex.substring(4, 6).toInt(16)
            val b = cleanedHex.substring(6, 8).toInt(16)
            Color(r, g, b, a)
        }

        else -> throw IllegalArgumentException("Invalid hex color format. Use #RRGGBB or #AARRGGBB")
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductVariant(
    modifier: Modifier = Modifier,
    item: OtherVariantItem? = null,
    currentProductId: String,
    onClickVariant: (String) -> Unit
) {
    item?.let {
        Column(modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                translateValue(item, "name"),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W700
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item.values?.let { list ->
                    items(list.count()) { index ->
                        val value = list[index]
                        val selected = value.variant_id.toString() == currentProductId
                        val color = if (selected)
                            MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surfaceDim
                        when (item.key) {
                            "color" -> {
                                val tooltipState = rememberTooltipState()
                                val scope = rememberCoroutineScope()



                                TooltipBox(
                                    state = tooltipState,
                                    positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
                                    tooltip = {
                                        RichTooltip(
                                            title = { Text("Smart Cycle") },
                                            text = { Text("If enabled, the cycle will automatically be detected, starting with the highest paycheck") }
                                        )
                                    },
                                ) {
                                    Box(Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                        .combinedClickable(
                                            onClick = {
                                                onClickVariant(value.variant_id.toString())
                                            },
                                            onLongClick = {
                                                scope.launch {
                                                    tooltipState.show(MutatePriority.Default)
                                                }
                                            }
                                        )
                                        .border(
                                            width = 2.dp,
                                            color = color,
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                        .padding(4.dp)
                                        .background(
                                            color = Color.fromHex(value.extra ?: "#FFFFFF"),
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                    )
                                }
                            }

                            else -> {
                                Box(Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .clickable {
                                        onClickVariant(value.variant_id.toString())
                                    }
                                    .border(
                                        width = 2.dp,
                                        color = color,
                                        shape = RoundedCornerShape(6.dp)
                                    )
                                    .padding(vertical = 8.dp, horizontal = 4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = translateValue(value, "value"),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.W700
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
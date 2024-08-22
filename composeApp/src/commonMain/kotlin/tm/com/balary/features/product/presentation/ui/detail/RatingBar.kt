package tm.com.balary.features.product.presentation.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    stars: Double = 0.0,
    starSize: Dp = 14.dp,
    spacing: Dp = 7.dp
) {
    val full = stars.toInt()
    val half = (stars % 5).toInt()
    Box(modifier) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            repeat(5) {
                Icon(
                    Icons.Outlined.StarOutline,
                    contentDescription = "star",
                    tint = Color(0xFFFFCF07),
                    modifier = Modifier.size(starSize)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            repeat(full) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "star",
                    tint = Color(0xFFFFCF07),
                    modifier = Modifier.size(starSize)
                )
            }
        }


    }

}
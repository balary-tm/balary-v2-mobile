package tm.com.balary.features.product.presentation.ui.detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Indicator(
    modifier: Modifier = Modifier,
    passiveColor: Color = MaterialTheme.colorScheme.surfaceDim,
    count: Int,
    current: Int
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(count) { index ->
            val color = animateColorAsState(if (current == index)
                MaterialTheme.colorScheme.primary
            else passiveColor)
            Box(
                Modifier.size(8.dp).background(
                    color = color.value,
                    shape = CircleShape
                ).clip(CircleShape)
            )
        }
    }
}
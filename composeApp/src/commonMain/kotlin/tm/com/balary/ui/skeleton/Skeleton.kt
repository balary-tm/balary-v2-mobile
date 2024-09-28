package tm.com.balary.ui.skeleton

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.ShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import com.valentinilk.shimmer.shimmerSpec

val appShimmerTheme: ShimmerTheme = ShimmerTheme(
    animationSpec = infiniteRepeatable(
        animation = shimmerSpec(
            durationMillis = 100,
            easing = LinearEasing,
            delayMillis = 500,
        ),
        repeatMode = RepeatMode.Restart,
    ),
    blendMode = BlendMode.DstIn,
    rotation = 15.0f,
    shaderColors = listOf(
        Color.Red.copy(alpha = 0.25f),
        Color.Red.copy(alpha = 1.00f),
        Color.Red.copy(alpha = 0.25f),
    ),
    shaderColorStops = listOf(
        0.0f,
        0.5f,
        1.0f,
    ),
    shimmerWidth = 400.dp,
)

@Composable
fun SkeletonRounded(
    modifier: Modifier = Modifier,
    borderRadius: Dp = 4.dp
) {
    val shimmer = rememberShimmer(ShimmerBounds.View)
    Box(modifier.clip(RoundedCornerShape(borderRadius)).shimmer().background(
        color = MaterialTheme.colorScheme.surfaceContainerLowest,
        shape = RoundedCornerShape(borderRadius)
    ))
}

@Composable
fun SkeletonCircle(
    modifier: Modifier = Modifier
) {
    Box(modifier.clip(CircleShape).shimmer().background(
        color = MaterialTheme.colorScheme.surfaceContainerLowest,
        shape = CircleShape
    ))
}
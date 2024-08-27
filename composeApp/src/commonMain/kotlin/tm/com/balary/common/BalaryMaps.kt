package tm.com.balary.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class LatLng(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

@Composable
expect fun BalaryMaps(modifier: Modifier, initialPoint: LatLng)
package tm.com.balary.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.location_bee
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getDrawableResourceBytes
import org.jetbrains.compose.resources.getSystemResourceEnvironment
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class LatLng(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

data class BalaryMarker @OptIn(ExperimentalUuidApi::class) constructor(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val title: String? = null,
    val layerKey: String= Uuid.random().toHexString(),
    var image: DrawableResource = Res.drawable.location_bee,
    val onClick: ()-> Unit
)

@OptIn(ExperimentalResourceApi::class)
suspend fun convertResourceToByteArray(resource: DrawableResource): ByteArray {
    val environment = getSystemResourceEnvironment()
    return getDrawableResourceBytes(environment, resource)
}


@OptIn(ExperimentalResourceApi::class)
suspend fun convertResourcesToByteArray(resources: List<DrawableResource>): List<ByteArray> {
    val environment = getSystemResourceEnvironment()
    return resources.map {
        getDrawableResourceBytes(environment, it)
    }
}

@Composable
expect fun BalaryMaps(
    modifier: Modifier,
    initialPoint: LatLng,
    markers: List<BalaryMarker> = emptyList(),
    onMapClick: () -> Unit = {}
)
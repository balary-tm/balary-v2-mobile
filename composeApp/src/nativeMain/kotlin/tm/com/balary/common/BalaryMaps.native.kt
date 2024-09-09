package tm.com.balary.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import cocoapods.MapLibre.MLNMapView
import cocoapods.MapLibre.MLNUserTrackingModeFollowWithHeading
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURL

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun BalaryMaps(
    modifier: Modifier,
    initialPoint: LatLng,
    onMapClick: () -> Unit
) {
    val coroutine = rememberCoroutineScope()
    val mapView = remember {
        MLNMapView().apply {
            styleURL = NSURL(
                string = "https://api.balary.net/maps/style/light.json"
            )
            showsUserLocation = true
            userTrackingMode = MLNUserTrackingModeFollowWithHeading
        }
    }

    UIKitView(
        factory = {
            mapView.apply {
                setDelegate(delegate)
            }
        },
        modifier = modifier,
        update = { map->
        }
    )
}
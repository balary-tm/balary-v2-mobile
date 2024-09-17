package tm.com.balary.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.mapboxsdk.maps.Style
import tm.com.balary.utils.rememberMapViewLifecycle

@Composable
actual fun BalaryMaps(
    modifier: Modifier,
    initialPoint: LatLng,
    onMapClick: () -> Unit
) {
    val map = rememberMapViewLifecycle()

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = {
            map.apply {
                getMapAsync { mapboxMap ->
                    mapboxMap.setStyle(Style.Builder().fromUri("asset://ligh.json")) { style->
                        mapboxMap.addOnMapClickListener {
                            onMapClick()
                            true
                        }
                    }
                    mapboxMap.uiSettings.isLogoEnabled = true
                    mapboxMap.uiSettings.isAttributionEnabled = true
                    mapboxMap.uiSettings.isCompassEnabled = true
                    mapboxMap.uiSettings.isTiltGesturesEnabled = true
                }
            }
        },
        update = {
            map.getMapAsync { mapboxMap->

            }
        }
    )
}
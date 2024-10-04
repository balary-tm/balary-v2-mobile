package tm.com.balary.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.mapboxsdk.maps.Style
import tm.com.balary.utils.rememberMapViewLifecycle

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.engine.LocationEngineRequest
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.mapbox.mapboxsdk.utils.BitmapUtils
import kotlinx.coroutines.launch
import tm.com.balary.R

fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

val BEE_MARKER_NAME = "bee_marker_image"

@Composable
actual fun BalaryMaps(
    modifier: Modifier,
    initialPoint: LatLng,
    markers: List<BalaryMarker>,
    onMapClick: () -> Unit
) {
    val map = rememberMapViewLifecycle()

    val coroutine = rememberCoroutineScope()

    val context = LocalContext.current

    fun buildLocationComponentActivationOptions(
        style: Style,
        locationComponentOptions: LocationComponentOptions
    ): LocationComponentActivationOptions {
        return LocationComponentActivationOptions
            .builder(context, style)
            .locationComponentOptions(locationComponentOptions)
            .useDefaultLocationEngine(true)
            .locationEngineRequest(
                LocationEngineRequest.Builder(750)
                    .setFastestInterval(750)
                    .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                    .build()
            )
            .build()
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = {
            map.apply {
                getMapAsync { mapboxMap ->


                    mapboxMap.setStyle(Style.Builder().fromUri("asset://ligh.json")) { style->
                        val drawable = ResourcesCompat.getDrawable(
                            context.resources,
                            R.drawable.location_bee,
                            null
                        )
                        style.addImage(BEE_MARKER_NAME, BitmapUtils.getBitmapFromDrawable(drawable)!!)

                        mapboxMap.addOnMapClickListener {
                            onMapClick()
                            true
                        }

                        try {
                            val locationComponent: LocationComponent? = mapboxMap.locationComponent
                            val locationComponentOptions =
                                LocationComponentOptions.builder(context)
                                    .pulseEnabled(true)
                                    .build()
                            val locationComponentActivationOptions =
                                buildLocationComponentActivationOptions(style, locationComponentOptions)
                            locationComponent!!.activateLocationComponent(locationComponentActivationOptions)
                            locationComponent!!.isLocationComponentEnabled = true
                            locationComponent!!.cameraMode = CameraMode.TRACKING
                        } catch (ex: Exception) {
                            ex.printStackTrace()
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
                mapboxMap.getStyle { style->
                    markers.forEach { marker->
                        coroutine.launch {
//                            val bytes = convertResourceToByteArray(marker.image)
//                            println(bytes.size)


                            // Create a SymbolManager
                            val symbolManager = SymbolManager(map, mapboxMap, style)
                            // Disable symbol collisions
                            symbolManager.iconAllowOverlap = true
                            symbolManager.iconIgnorePlacement = true

                            // Add a new symbol at specified lat/lon.
                            val symbol = symbolManager.create(
                                SymbolOptions()
                                    .withLatLng(com.mapbox.mapboxsdk.geometry.LatLng(marker.latitude, marker.longitude))
                                    .withIconImage(BEE_MARKER_NAME)
                                    .withIconSize(0.7f)
                                    .withIconAnchor("bottom")
                            )
                            symbolManager.update(symbol)

                            // Add a listener to trigger markers clicks.
                            symbolManager.addClickListener {
                                // Display information
                                marker.onClick()
                                true
                            }
                        }
                    }
                }
            }
        }
    )
}
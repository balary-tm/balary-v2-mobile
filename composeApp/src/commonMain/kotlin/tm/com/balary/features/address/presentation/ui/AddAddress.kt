package tm.com.balary.features.address.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.location_fill
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.common.BalaryMaps
import tm.com.balary.common.LatLng
import tm.com.balary.features.auth.presentation.ui.BackScreen
import tm.com.balary.features.home.presentation.ui.banner.SearchInput

class AddAddress : Screen {
    @Composable
    override fun Content() {

        val show = remember {
            mutableStateOf(false)
        }

        val navigator = LocalNavigator.currentOrThrow

        SelectThisLocation(
            show = show.value,
            onDismiss = {
                show.value = false
            },
            onSelect = {
                show.value = false
                navigator.push(AddressFields())
            }
        )

        BackScreen(
            Modifier.fillMaxSize(),
            title = "Salgy goşmak",
            spacing = 0.dp
        ) {
            Column(Modifier.fillMaxSize().background(
                color = MaterialTheme.colorScheme.surface
            )) {
                Box(Modifier.padding(16.dp).fillMaxWidth()) {
                    SearchInput(Modifier.fillMaxWidth()) {

                    }
                }

                Box(
                    Modifier.fillMaxSize()
                ) {
                    BalaryMaps(
                        modifier = Modifier.fillMaxSize(),
                        initialPoint = LatLng(0.0,0.0),
                        onMapClick = {
                            show.value = true
                        }
                    )

                    FloatingActionButton(
                        shape = CircleShape,
                        containerColor = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.zIndex(10f).align(Alignment.BottomEnd).offset(x = (-16).dp, y = (-16).dp),
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.location_fill),
                            contentDescription = "my location",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                }

            }
        }
    }
}
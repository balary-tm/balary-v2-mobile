package tm.com.balary.features.map.presentation.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.location_fill
import balary.composeapp.generated.resources.wallet
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.common.BalaryMaps
import tm.com.balary.common.LatLng
import tm.com.balary.features.home.presentation.ui.banner.SearchInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapDialog(
    open: Boolean = false,
    onClose: () -> Unit
) {
    if (open) {
        val state = rememberModalBottomSheetState()
        val corner =
            animateDpAsState(if (state.currentValue == SheetValue.PartiallyExpanded) 20.dp else 0.dp)
        ModalBottomSheet(
            sheetState = state,
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(
                topStart = corner.value,
                topEnd = corner.value
            ),
            tonalElevation = 0.dp,
            onDismissRequest = {
                onClose()
            }
        ) {
            Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SearchInput(
                    modifier = Modifier.fillMaxWidth(),
                    onSearch = {

                    },
                    showScan = false
                )
                Box(
                    Modifier.fillMaxWidth().weight(1f).border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(20.dp)
                    ).clip(RoundedCornerShape(20.dp))
                ) {
                    BalaryMaps(Modifier.fillMaxSize(), initialPoint = LatLng())

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

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {

                    }
                ) {
                    Text("Kabul etmek", style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700
                    ), color = MaterialTheme.colorScheme.onPrimary)
                }

                Spacer(Modifier.height(50.dp))
            }
        }
    }
}
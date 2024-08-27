package tm.com.balary.features.address.presentation.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectAddressDialog(
    open: Boolean = false,
    onClose: () -> Unit
) {
    if (open) {
        val state = rememberModalBottomSheetState()
        val corner = animateDpAsState(if(state.currentValue == SheetValue.PartiallyExpanded) 20.dp else 0.dp)
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
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(
                    color = MaterialTheme.colorScheme.surface
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                item {
                    Text(
                        "Salgylarym",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
                items(20) {
                    AddressItem(Modifier.fillMaxWidth())
                }
                item {
                    Spacer(Modifier.height(50.dp))
                }
            }
        }
    }
}
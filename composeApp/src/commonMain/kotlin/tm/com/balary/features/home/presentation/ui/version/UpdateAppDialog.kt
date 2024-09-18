package tm.com.balary.features.home.presentation.ui.version

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import cafe.adriel.lyricist.LocalStrings
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.home.domain.model.VersionCheckModel
import tm.com.balary.ui.ImageLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateAppDialog(
    show: Boolean = false,
    versions: List<VersionCheckModel>,
    onClose: () -> Unit
) {
    val strings = LocalStrings.current
    if (show) {
        val version = versions.last()
        val isRequired = versions.any { it.is_required }
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
                //if(isRequired.not()) {
                    onClose()
                //}
            }
        ) {
            LazyColumn(
                modifier = Modifier.navigationBarsPadding().fillMaxSize().background(
                    color = MaterialTheme.colorScheme.surface
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                item {
                    Text(
                        version.title_tm,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W700
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                item {
                    Text(
                        version.description_tm,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ),
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                item {
                    Spacer(Modifier.height(22.dp))
                }
                item {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {

                        }
                    ) {
                        Text(
                            strings.download,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                item {
                    Spacer(Modifier.height(50.dp))
                }
            }
        }
    }
}
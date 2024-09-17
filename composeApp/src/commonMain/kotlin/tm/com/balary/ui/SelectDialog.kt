package tm.com.balary.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun SelectDialog(
    show: Boolean = false,
    selectedIndex: Int = 0,
    onDismiss: () -> Unit,
    onSelect: (Int) -> Unit,
    items: List<String>,
    title: String,
) {
    if (show) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = true)
        ) {
            Column(
                Modifier.fillMaxWidth().background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    title, style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700,
                        fontSize = 18.sp
                    ), color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(16.dp)
                )


                repeat(items.count()) { index ->
                    Row(
                        Modifier.fillMaxWidth().clickable {
                            onSelect(index)
                        }.padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            items[index], style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700,
                                fontSize = 18.sp
                            ), color = MaterialTheme.colorScheme.onSurface
                        )

                        AppCheckBox(
                            checked = selectedIndex == index,
                            onChange = {

                            }
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))


            }
        }
    }
}
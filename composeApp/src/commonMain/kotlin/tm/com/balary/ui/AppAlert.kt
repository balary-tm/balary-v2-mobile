package tm.com.balary.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.lyricist.LocalStrings

enum class AppAlertType {
    INFO,
    DANGER
}

@Composable
fun AppAlert(
    show: Boolean = false,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit = {},
    title: String,
    message: AnnotatedString,
    type: AppAlertType = AppAlertType.INFO
) {
    val strings = LocalStrings.current
    if (show) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = true)
        ) {
            Column(
                Modifier.fillMaxWidth().background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                ).padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    title, style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700,
                        fontSize = 18.sp
                    ), color = MaterialTheme.colorScheme.primary
                )

                Text(
                    message,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {

                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(5.dp),
                        onClick = {
                            onDismiss()
                        },
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.primary,
                                )
                            )
                        )
                    ) {
                        Text(
                            strings.no,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Button(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(5.dp),
                        onClick = {
                            onDismiss()
                            onConfirm()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (type == AppAlertType.DANGER)
                                MaterialTheme.colorScheme.error
                            else MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            strings.yes,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                }


            }
        }
    }
}
package tm.com.balary.features.basket.presentation.ui.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.order_bee
import cafe.adriel.lyricist.LocalStrings
import org.jetbrains.compose.resources.painterResource

@Composable
fun OrderSuccess(
    open: Boolean = false,
    onClose: () -> Unit
) {
    val strings = LocalStrings.current
    if(open) {
        Dialog(
            onDismissRequest = onClose,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(Modifier.fillMaxSize().padding(top = 50.dp).padding(horizontal = 16.dp), contentAlignment = Alignment.TopCenter) {

                Box(Modifier.zIndex(2f).size(100.dp).background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = CircleShape
                ))
                Image(
                    painter = painterResource(Res.drawable.order_bee),
                    contentDescription = "order-bee",
                    modifier = Modifier.size(90.dp).zIndex(3f)
                )

                Column(Modifier.fillMaxWidth().zIndex(1f).padding(top = 50.dp).background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                ).padding(16.dp)) {

                    Spacer(Modifier.height(50.dp))

                    Text(
                        strings.thanks,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W700
                        )
                    )

                    Text(
                        strings.orderAccepted,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W700
                        )
                    )

                    Text(
                        "${strings.orderNumber}: #26491",
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.height(10.dp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(Modifier.height(10.dp))

                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        SuccessText(
                            modifier = Modifier.weight(1f),
                            text = strings.day,
                            value = "15.08.2024"
                        )
                        SuccessText(
                            modifier = Modifier.weight(1f),
                            text = strings.courierTime,
                            value = "10:00-12:00",
                            alignment = Alignment.End
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    SuccessText(
                        modifier = Modifier.fillMaxWidth(),
                        text = strings.paymentType,
                        value = strings.cash
                    )
                    Spacer(Modifier.height(16.dp))

                    SuccessText(
                        modifier = Modifier.fillMaxWidth(),
                        text = strings.address,
                        value = "Mir 7/4 13 nji ýaşaýyş jaý 2 nji kw"
                    )
                    Spacer(Modifier.height(16.dp))

                    SuccessText(
                        modifier = Modifier.fillMaxWidth(),
                        text = strings.phone,
                        value = "+993 62 566987"
                    )
                    Spacer(Modifier.height(16.dp))

                    SuccessText(
                        modifier = Modifier.fillMaxWidth(),
                        text = strings.deliveryService,
                        value = "20.00 ${strings.money}"
                    )
                    Spacer(Modifier.height(16.dp))

                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        SuccessText(
                            modifier = Modifier.weight(1f),
                            text = strings.total,
                            value = "549.00 ${strings.money}"
                        )
                        SuccessText(
                            modifier = Modifier.weight(1f),
                            text = strings.status,
                            value = strings.checking,
                            alignment = Alignment.End
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Text(strings.accept, style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W700
                        ), color = MaterialTheme.colorScheme.onSurface)
                    }



                }





            }
        }
    }
}
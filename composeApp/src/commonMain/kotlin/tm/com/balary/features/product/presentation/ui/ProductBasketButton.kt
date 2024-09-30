package tm.com.balary.features.product.presentation.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.IndeterminateCheckBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.add_basket
import cafe.adriel.lyricist.LocalStrings
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinNavViewModel
import tm.com.balary.features.basket.presentation.viewmodel.BasketViewModel

@Composable
fun ProductBasketButton(
    modifier: Modifier = Modifier,
    initialCount: Int = 0,
    bigButton: Boolean = false,
    onCountChange: (Int) -> Unit
) {
    val count = remember(initialCount) {
        mutableStateOf(initialCount)
    }
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = {
            if (count.value == 0) {
                count.value = 1
                onCountChange(1)
            }
        }
    ) {
        AnimatedContent(count.value) {
            if (it > 0) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        Icons.Filled.IndeterminateCheckBox,
                        contentDescription = "minus",
                        modifier = Modifier.size(20.dp).clickable {
                            onCountChange(count.value.minus(1))
                            count.value = count.value.minus(1)
                        }
                    )

                    Text(
                        text = count.value.toString(),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W700
                        )
                    )

                    Icon(
                        Icons.Filled.AddBox,
                        contentDescription = "add",
                        modifier = Modifier.size(20.dp).clickable {
                            onCountChange(count.value.plus(1))
                            count.value = count.value.plus(1)
                        }
                    )
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(Res.drawable.add_basket),
                        contentDescription = "basket"
                    )

                    if(bigButton) {
                        Text(
                            LocalStrings.current.addToBasket,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            ),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

package tm.com.balary.features.product.presentation.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductInfo(
    modifier: Modifier = Modifier,
    titleSize: TextUnit = 14.sp,
    valueSize: TextUnit = 14.sp,
    title: String,
    value: String
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        val end = if(title.endsWith(":")) "" else ":"

        val text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontWeight = FontWeight.W700,
                    fontSize = titleSize,
                    color = MaterialTheme.colorScheme.onSurface
                )
            ) {
                append(title.plus(end))
            }
            append(" ")
            withStyle(
                SpanStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = valueSize,
                    color = MaterialTheme.colorScheme.onSurface
                )
            ) {
                append(value)
            }
        }
        Text(
            text
        )
    }
}
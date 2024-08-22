package tm.com.balary.features.product.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProductTag(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.error,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                bottomStart = 20.dp
            )
        ).padding(4.dp),
        text = text,
        color = Color.White,
        style = MaterialTheme.typography.bodySmall
    )
}
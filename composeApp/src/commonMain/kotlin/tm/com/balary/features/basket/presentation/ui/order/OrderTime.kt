package tm.com.balary.features.basket.presentation.ui.order

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun OrderTime(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    title: String
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.W700
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        AnimatedVisibility(selected) {
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
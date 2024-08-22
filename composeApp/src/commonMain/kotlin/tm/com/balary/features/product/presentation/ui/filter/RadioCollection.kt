package tm.com.balary.features.product.presentation.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RadioCollection(
    modifier: Modifier = Modifier,
    title: String,
    items: Map<String, Any>
) {
    Column(
        modifier.background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(
                bottomEnd = 20.dp,
                bottomStart = 20.dp
            )
        ).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W700
                )
            )

            Text(
                text = "Ählisini gör",
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall
            )
        }

        items.entries.forEachIndexed { index, entry ->
            RadioText(
                modifier = Modifier.fillMaxWidth(),
                checked = index == 0,
                text = entry.key
            )
        }
    }
}
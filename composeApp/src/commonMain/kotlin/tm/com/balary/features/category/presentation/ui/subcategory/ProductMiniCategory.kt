package tm.com.balary.features.category.presentation.ui.subcategory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.fruit
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.ui.ImageLoader

@Composable
fun ProductMiniCategory(
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.clip(RoundedCornerShape(10.dp)).background(
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
        shape = RoundedCornerShape(10.dp)
    ).clickable {

    }.padding(vertical = 2.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        ImageLoader(
            modifier = Modifier.size(48.dp),
            url = "",
            placeholder = painterResource(Res.drawable.fruit)
        )

        Text(
            "Miweler",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W700
            ),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
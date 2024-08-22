package tm.com.balary.features.product.presentation.ui.review

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.product.presentation.ui.detail.RatingBar
import tm.com.balary.ui.ImageLoader

@Composable
fun MiniReview(
    modifier: Modifier = Modifier,
    image: String,
    username: String,
    stars: Double,
    date: String,
    review: String
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0x99A3AE66),
                shape = RoundedCornerShape(4.dp)
            )
            .clip(RoundedCornerShape(4.dp))
            .clickable {

            }
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ImageLoader(
            modifier = Modifier.size(40.dp).clip(CircleShape).border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape
            ).shadow(1.dp, shape = CircleShape),
            url = image,
            placeholder = painterResource(Res.drawable.banner),
            contentScale = ContentScale.Crop
        )

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                username,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W700
                ),
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                RatingBar(
                    stars = stars,
                    starSize = 16.dp,
                    spacing = 5.dp
                )

                Text(
                    date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            Text(
                review,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )


        }
    }
}
package tm.com.balary.features.home.presentation.ui.brand

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import tm.com.balary.ui.ImageLoader

@Composable
fun BrandItem(
    modifier: Modifier = Modifier,
    image: String
) {
    ImageLoader(
        modifier = modifier.size(60.dp).border(
            width = 2.dp,
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceDim
        ).clip(RoundedCornerShape(12.dp)).padding(1.dp),
        url = image,
        contentScale = ContentScale.FillBounds
    )
}
package tm.com.balary.features.ads.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.banner
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.ui.ImageLoader

@Composable
fun AdsComponent(
    modifier: Modifier = Modifier,
    image: String
) {
    val shape = RoundedCornerShape(10.dp)
    ImageLoader(
        modifier = modifier.clip(shape).clickable {

        },
        url = image,
        placeholder = painterResource(Res.drawable.banner),
        contentScale = ContentScale.FillBounds
    )
}
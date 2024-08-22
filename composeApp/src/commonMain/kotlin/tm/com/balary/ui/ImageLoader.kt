package tm.com.balary.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.location
import balary.composeapp.generated.resources.placeholder
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.Fit,
    placeholder: Painter = painterResource(Res.drawable.placeholder)
) {
    AsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = "url-image",
        contentScale = contentScale,
        placeholder = placeholder,
        error = placeholder
    )
}
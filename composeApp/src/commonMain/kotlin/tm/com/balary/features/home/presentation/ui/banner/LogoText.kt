package tm.com.balary.features.home.presentation.ui.banner

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.logo_text
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource

@Composable
fun LogoText(
    modifier: Modifier = Modifier,
    url: String? = null
) {
    AsyncImage(
        modifier = modifier.height(50.dp),
        model = url,
        contentDescription = "Logo",
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(Res.drawable.logo_text),
        error = painterResource(Res.drawable.logo_text)
    )
}
package tm.com.balary.features.auth.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.topbar_bg
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.home.presentation.ui.banner.LogoText

@Composable
fun AppToolbar(
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)



    Box(
        modifier = modifier.background(Color.Transparent).background(
            color = MaterialTheme.colorScheme.tertiary,
            shape = shape
        ).clip(shape).height(170.dp)
    ) {
        LogoText(
            modifier = Modifier.offset(y = 16.dp).align(Alignment.TopCenter).width(170.dp).height(50.dp),
            contentScale = ContentScale.Fit
        )
        Image(
            painter = painterResource(Res.drawable.topbar_bg),
            contentDescription = "toolbar",
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth
        )
    }
}
package tm.com.balary.features.onboarding.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.blur_kind
import balary.composeapp.generated.resources.kind
import org.jetbrains.compose.resources.painterResource

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier.fillMaxSize()) {
        Box(Modifier.fillMaxWidth().fillMaxHeight(0.7f)) {

            Box(Modifier.size(276.dp).align(Alignment.TopStart).offset(
                x = (-120).dp,
                y = (-120).dp
            ).background(
                color = Color(0x101caa23),
                shape = CircleShape
            ))

            Box(Modifier.size(276.dp).align(Alignment.CenterEnd).offset(
                x = 120.dp,
                y = 120.dp
            ).background(
                color = Color(0x101caa23),
                shape = CircleShape
            ))

            Box(Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(Res.drawable.kind),
                    contentDescription = "kind",
                    modifier = Modifier
                        .width(45.dp)
                        .height(38.dp)
                        .alpha(0.4f)
                        .rotate(-102f)
                        .align(Alignment.TopEnd)
                )

                Image(
                    painter = painterResource(Res.drawable.blur_kind),
                    contentDescription = "kind",
                    modifier = Modifier
                        .width(45.dp)
                        .height(38.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = 12.dp)
                )

                Image(
                    painter = painterResource(Res.drawable.kind),
                    contentDescription = "kind",
                    modifier = Modifier
                        .width(33.dp)
                        .height(28.dp)
                        .align(Alignment.BottomStart)
                        .offset(x = 22.dp)
                )
            }


        }
        content()
    }
}
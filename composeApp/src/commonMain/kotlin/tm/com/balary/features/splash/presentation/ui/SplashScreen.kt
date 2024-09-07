package tm.com.balary.features.splash.presentation.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.logo_text
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val opened = rememberSaveable {
        mutableStateOf(false)
    }
    val scaled = rememberSaveable {
        mutableStateOf(false)
    }
    val scale = animateFloatAsState(
        if(scaled.value) 1f else 10f,
        animationSpec = tween(
            durationMillis = 1000,
        )
    )

    LaunchedEffect(Unit) {
        delay(100L)
        scaled.value = true
        delay(3000L)
        opened.value = true
    }
    if(opened.value) {
        content()
    } else {
        Box(modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.surface
        ), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(Res.drawable.logo_text),
                contentDescription = "logo",
                modifier = Modifier.width(250.dp).height(76.dp).scale(scale.value).graphicsLayer {
                    alpha = if(scaled.value) 1f else 0f
                },
                contentScale = ContentScale.Fit
            )
        }
    }
}
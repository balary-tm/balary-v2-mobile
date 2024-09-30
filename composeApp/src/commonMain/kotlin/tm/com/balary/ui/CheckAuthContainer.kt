package tm.com.balary.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import tm.com.balary.features.auth.presentation.ui.AuthScreen
import tm.com.balary.state.LocalAuth

@Composable
fun CheckAuthContainer(
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
) {
    val authState = LocalAuth.current
    val strings = LocalStrings.current
    val navigator = LocalNavigator.currentOrThrow
    if(authState.value.logged) {
        content()
    } else {
        AppError(
            modifier = modifier.fillMaxSize(),
            text = strings.pleaseAuth,
            showAction = true,
            action = {
                Button(
                    onClick = {
                        navigator.push(AuthScreen())
                    }
                ) {
                    Text(strings.signIn)
                }
            }
        )
    }
}

@Composable
fun CheckAuthFeature(
    verticalPadding: Dp = 0.dp,
    content: @Composable () -> Unit,
) {
    val authState = LocalAuth.current
    if(authState.value.logged) {
        content()
    } else {
        Box(Modifier.height(verticalPadding))
    }
}
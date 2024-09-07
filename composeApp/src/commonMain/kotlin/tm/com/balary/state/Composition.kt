package tm.com.balary.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import tm.com.balary.router.Router

@Composable
fun Composition(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDarkMode provides rememberSaveable {
            mutableStateOf(false)
        },
        LocalAuth provides remember {
            mutableStateOf(AuthState())
        }
    ) {
        content()
    }
}
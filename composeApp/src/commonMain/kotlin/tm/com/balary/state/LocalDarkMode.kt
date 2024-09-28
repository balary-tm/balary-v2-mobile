package tm.com.balary.state

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf

val LocalDarkMode = staticCompositionLocalOf {
    mutableStateOf(false)
}
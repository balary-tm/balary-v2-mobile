package tm.com.balary.state

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

val LocalDarkMode = compositionLocalOf {
    mutableStateOf(false)
}
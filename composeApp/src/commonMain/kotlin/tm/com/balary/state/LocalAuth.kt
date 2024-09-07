package tm.com.balary.state

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

data class AuthState(
    val logged: Boolean = true
)

val LocalAuth = compositionLocalOf {
    mutableStateOf(AuthState())
}
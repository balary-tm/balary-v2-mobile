package tm.com.balary.state

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import tm.com.balary.features.profile.domain.model.AppState

val LocalAppState = compositionLocalOf {
    mutableStateOf(AppState())
}
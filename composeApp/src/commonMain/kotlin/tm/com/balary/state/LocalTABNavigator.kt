package tm.com.balary.state

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.navigator.tab.Tab

val LocalTABNavigator = compositionLocalOf {
    mutableStateOf<Tab?>(null)
}
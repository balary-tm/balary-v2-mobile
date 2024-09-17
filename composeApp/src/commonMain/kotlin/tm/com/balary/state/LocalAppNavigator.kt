package tm.com.balary.state

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController

val LocalAppNavigator = compositionLocalOf {
    mutableStateOf<NavHostController?>(null)
}

val LocalHomeNavigator = compositionLocalOf {
    mutableStateOf<NavHostController?>(null)
}

val LocalCategoryNavigator = compositionLocalOf {
    mutableStateOf<NavHostController?>(null)
}
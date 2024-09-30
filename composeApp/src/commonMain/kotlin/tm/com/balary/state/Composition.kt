package tm.com.balary.state

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.navigator.tab.Tab
import org.koin.compose.koinInject
import tm.com.balary.features.auth.data.settings.AuthSettings
import tm.com.balary.features.home.presentation.ui.HomeTab
import tm.com.balary.features.profile.data.setting.AppSettings
import tm.com.balary.features.profile.domain.model.AppState
import tm.com.balary.features.profile.domain.model.AppTheme

@Composable
fun Composition(
    content: @Composable () -> Unit
) {
    val appNavHostController = rememberNavController()
    val homeNavHostController = rememberNavController()
    val categoryNavHostController = rememberNavController()
    val appSettings: AppSettings = koinInject()
    val authSettings: AuthSettings = koinInject()
    val isSystemDark = isSystemInDarkTheme()
    val isDark = when(appSettings.getTheme()) {
        AppTheme.SYSTEM -> isSystemDark
        AppTheme.DARK -> true
        AppTheme.LIGHT -> false
    }
    CompositionLocalProvider(
        values = arrayOf(
            LocalAppNavigator provides remember {
                mutableStateOf(appNavHostController)
            },
            LocalHomeNavigator provides remember {
                mutableStateOf(homeNavHostController)
            },
            LocalCategoryNavigator provides remember {
                mutableStateOf(categoryNavHostController)
            },
            LocalDarkMode provides rememberSaveable {
                mutableStateOf(isDark)
            },
            LocalAuth provides remember {
                mutableStateOf(AuthState(logged = authSettings.getToken().isNullOrEmpty().not()))
            },
            LocalTABNavigator provides remember {
                mutableStateOf(HomeTab)
            },
            LocalAppState provides remember {
                mutableStateOf(appSettings.getAppSettings())
            }
        )
    ) {
        content()
    }
}
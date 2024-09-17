import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.koinApplication
import tm.com.balary.core.provideAppSettings
import tm.com.balary.core.provideHttpClient
import tm.com.balary.features.contact.di.chatModule
import tm.com.balary.features.home.di.homeModule
import tm.com.balary.features.onboarding.presentation.ui.OnBoardingScreen
import tm.com.balary.features.profile.di.profileModule
import tm.com.balary.features.splash.presentation.ui.SplashScreen
import tm.com.balary.router.AppTabScreen
import tm.com.balary.state.Composition
import tm.com.balary.state.LocalAppState
import tm.com.balary.state.LocalDarkMode


fun koinConfiguration() = koinApplication {
    modules(
        provideAppSettings,
        provideHttpClient,
        homeModule,
        chatModule,
        profileModule
    )
}


@Preview
@Composable
fun App(modifier: Modifier = Modifier) {
    KoinApplication(::koinConfiguration) {
        val lyricist = rememberStrings()
        ProvideStrings(lyricist) {
            Box(modifier = modifier) {
                Composition {
                    val appState = LocalAppState.current
                    val isDark = LocalDarkMode.current
                    AppTheme(darkTheme = isDark.value) {
                        SplashScreen {
                            if(appState.value.isFirst){
                                Navigator(OnBoardingScreen())
                            } else {
                                Navigator(AppTabScreen())
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}
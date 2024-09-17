package tm.com.balary.features.profile.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.address
import balary.composeapp.generated.resources.contact
import balary.composeapp.generated.resources.exit
import balary.composeapp.generated.resources.info
import balary.composeapp.generated.resources.language
import balary.composeapp.generated.resources.orders
import balary.composeapp.generated.resources.payment
import balary.composeapp.generated.resources.privacy
import balary.composeapp.generated.resources.profile
import balary.composeapp.generated.resources.theme
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import tm.com.balary.features.about.presentation.ui.AboutScreen
import tm.com.balary.features.address.presentation.ui.AddressScreen
import tm.com.balary.features.contact.presentation.ui.ChatScreen
import tm.com.balary.features.order.presentation.ui.OrderHistoryScreen
import tm.com.balary.features.payment.presentation.ui.PaymentScreen
import tm.com.balary.features.privacy.presentation.ui.PrivacyScreen
import tm.com.balary.features.profile.data.setting.AppSettings
import tm.com.balary.features.profile.domain.model.AppTheme
import tm.com.balary.features.profile.presentation.ui.profile.ProfileButton
import tm.com.balary.features.profile.presentation.ui.profile.ProfileToolbar
import tm.com.balary.locale.Locales
import tm.com.balary.router.Router
import tm.com.balary.state.LocalAppState
import tm.com.balary.state.LocalAuth
import tm.com.balary.state.LocalDarkMode
import tm.com.balary.ui.AppAlert
import tm.com.balary.ui.AppAlertType
import tm.com.balary.ui.SelectDialog

data class ProfileItem(
    val icon: Painter,
    val text: String,
    val notificationCount: Int = 0,
    val onClick: () -> Unit
)

object ProfileTab : Tab {

    override val key: ScreenKey
        get() = Router.PROFILE_ROUTE

    @Composable
    override fun Content() {
        Navigator(ProfileScreen()) {
            SlideTransition(it)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val strings = LocalStrings.current
            return TabOptions(
                index = Router.PROFILE,
                title = strings.profile,
                icon = painterResource(Res.drawable.profile)
            )
        }
}

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
    }

}

@Composable
fun Profile(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val strings = LocalStrings.current
    val authState = LocalAuth.current

    val openLanguage = remember {
        mutableStateOf(false)
    }

    val openTheme = remember {
        mutableStateOf(false)
    }

    val logout = remember {
        mutableStateOf(false)
    }

    val darkTheme = LocalDarkMode.current
    val isSystemInDark = isSystemInDarkTheme()

    AppAlert(
        show = logout.value,
        onDismiss = {
            logout.value = false
        },
        title = strings.attention,
        message = buildAnnotatedString {
            append(strings.wantLogout)
        },
        type = AppAlertType.DANGER
    )

    val appState = LocalAppState.current
    val appSettings: AppSettings = koinInject()

    SelectDialog(
        show = openLanguage.value,
        selectedIndex = if(appState.value.language == Locales.RUSSIAN) 1 else 0,
        title = strings.selectLanguage,
        items = listOf(
            strings.turkmen,
            strings.russian
        ),
        onDismiss = {
            openLanguage.value = false
        },
        onSelect = {index->
            if(index==1){
                appSettings.saveLanguage(Locales.RUSSIAN)
                appState.value = appState.value.copy(
                    language = Locales.RUSSIAN
                )
            } else {
                appSettings.saveLanguage(Locales.TURKMEN)
                appState.value = appState.value.copy(
                    language = Locales.TURKMEN
                )
            }
        }
    )

    SelectDialog(
        show = openTheme.value,
        selectedIndex = when(appState.value.theme){
            AppTheme.SYSTEM -> 0
            AppTheme.DARK -> 2
            AppTheme.LIGHT -> 1
        },
        title = strings.selectTheme,
        items = listOf(
            strings.sameAsSystem,
            strings.lightTheme,
            strings.darkTheme,
        ),
        onDismiss = {
            openTheme.value = false
        },
        onSelect = {index->
            when (index) {
                0 -> {
                    darkTheme.value = isSystemInDark
                    appSettings.saveTheme(AppTheme.SYSTEM)
                    appState.value = appState.value.copy(
                        theme = AppTheme.SYSTEM
                    )
                }
                1 -> {
                    darkTheme.value = false
                    appSettings.saveTheme(AppTheme.LIGHT)
                    appState.value = appState.value.copy(
                        theme = AppTheme.LIGHT
                    )
                }
                else -> {
                    darkTheme.value = true
                    appSettings.saveTheme(AppTheme.DARK)
                    appState.value = appState.value.copy(
                        theme = AppTheme.DARK
                    )
                }
            }
        }
    )

    val list = listOf(
        ProfileItem(
            icon = painterResource(Res.drawable.address),
            text = strings.myAddresses,
            onClick = {
                navHostController.navigate(tm.com.balary.router.AddressScreen)
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.orders),
            text = strings.myOrders,
            onClick = {
                navHostController.navigate(tm.com.balary.router.OrderHistoryScreen)
            }
        ),
//        ProfileItem(
//            icon = painterResource(Res.drawable.shop),
//            text = "Halan dükanlarym",
//            onClick = {}
//        ),
//        ProfileItem(
//            icon = painterResource(Res.drawable.buisness),
//            text = "Meniň biznesym",
//            notificationCount = 20,
//            onClick = {}
//        ),
        ProfileItem(
            icon = painterResource(Res.drawable.payment),
            text = strings.myPayment,
            notificationCount = 15,
            onClick = {
                navHostController.navigate(tm.com.balary.router.PaymentScreen)
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.language),
            text = strings.language,
            onClick = {
                openLanguage.value = true
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.theme),
            text = strings.theme,
            onClick = {
                openTheme.value = true
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.contact),
            text = strings.contactWithMe,
            notificationCount = 8,
            onClick = {
                navHostController.navigate(tm.com.balary.router.ChatScreen)
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.info),
            text = strings.aboutUs,
            onClick = {
                navHostController.navigate(tm.com.balary.router.AboutScreen)
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.privacy),
            text = strings.privacy,
            onClick = {
                navHostController.navigate(tm.com.balary.router.PrivacyScreen)
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.exit),
            text = strings.logout,
            onClick = {
                logout.value = true
                authState.value = authState.value.copy(
                    logged = false
                )
            }
        )
    )
    Column(modifier = modifier.background(
        color = MaterialTheme.colorScheme.background
    ).verticalScroll(rememberScrollState())) {
        ProfileToolbar(Modifier.fillMaxWidth(), navHostController = navHostController)
        Spacer(Modifier.height(8.dp))
        Column(Modifier.background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(20.dp)
        ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            repeat(list.count()) { index->
                ProfileButton(
                    Modifier.fillMaxWidth(),
                    icon = list[index].icon,
                    text = list[index].text,
                    notification = list[index].notificationCount,
                    onClick = list[index].onClick
                )
            }
        }
    }
}
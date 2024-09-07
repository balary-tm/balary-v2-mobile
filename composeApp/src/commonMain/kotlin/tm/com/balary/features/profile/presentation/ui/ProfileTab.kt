package tm.com.balary.features.profile.presentation.ui

import androidx.compose.foundation.background
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
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.address
import balary.composeapp.generated.resources.buisness
import balary.composeapp.generated.resources.contact
import balary.composeapp.generated.resources.exit
import balary.composeapp.generated.resources.info
import balary.composeapp.generated.resources.language
import balary.composeapp.generated.resources.orders
import balary.composeapp.generated.resources.payment
import balary.composeapp.generated.resources.privacy
import balary.composeapp.generated.resources.profile
import balary.composeapp.generated.resources.shop
import balary.composeapp.generated.resources.theme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.about.presentation.ui.AboutScreen
import tm.com.balary.features.address.presentation.ui.AddressScreen
import tm.com.balary.features.contact.presentation.ui.ChatScreen
import tm.com.balary.features.order.presentation.ui.OrderHistory
import tm.com.balary.features.order.presentation.ui.OrderHistoryScreen
import tm.com.balary.features.payment.presentation.ui.PaymentScreen
import tm.com.balary.features.privacy.presentation.ui.PrivacyScreen
import tm.com.balary.features.profile.presentation.ui.profile.ProfileButton
import tm.com.balary.features.profile.presentation.ui.profile.ProfileToolbar
import tm.com.balary.router.Router
import tm.com.balary.state.LocalAuth
import tm.com.balary.ui.AppAlert
import tm.com.balary.ui.AppAlertType
import tm.com.balary.ui.SelectDialog
import kotlin.math.log

data class ProfileItem(
    val icon: Painter,
    val text: String,
    val notificationCount: Int = 0,
    val onClick: () -> Unit
)

object ProfileTab : Tab {
    @Composable
    override fun Content() {
        Navigator(ProfileScreen()) {
            SlideTransition(it)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            return TabOptions(
                index = Router.PROFILE,
                title = "Profil",
                icon = painterResource(Res.drawable.profile)
            )
        }
}

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        Profile(Modifier.fillMaxSize())
    }

}

@Composable
fun Profile(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.currentOrThrow
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

    AppAlert(
        show = logout.value,
        onDismiss = {
            logout.value = false
        },
        title = "Duýduruş",
        message = buildAnnotatedString {
            append("Siz hakykatdanam ulgamdan çykmak isleýärsiňizmi?")
        },
        type = AppAlertType.DANGER
    )

    SelectDialog(
        show = openLanguage.value,
        title = "Dil saýlaň",
        items = listOf(
            "Türkmen dili",
            "Rus dili"
        ),
        onDismiss = {
            openLanguage.value = false
        },
        onSelect = {index->

        }
    )

    SelectDialog(
        show = openTheme.value,
        title = "Tema saýlaň",
        items = listOf(
            "Ulgamda bolşy ýaly",
            "Açyk tema",
            "Gara tema",
        ),
        onDismiss = {
            openTheme.value = false
        },
        onSelect = {index->

        }
    )

    val list = listOf(
        ProfileItem(
            icon = painterResource(Res.drawable.address),
            text = "Salgylarym",
            onClick = {
                navigator.push(AddressScreen())
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.orders),
            text = "Sargytlarym",
            onClick = {
                navigator.push(OrderHistoryScreen())
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
            text = "Meniň hasabym",
            notificationCount = 15,
            onClick = {
                navigator.push(PaymentScreen())
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.language),
            text = "Dil",
            onClick = {
                openLanguage.value = true
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.theme),
            text = "Tema saýlaň",
            onClick = {
                openTheme.value = true
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.contact),
            text = "Biziň bilen habarlaşyň",
            notificationCount = 8,
            onClick = {
                navigator.push(ChatScreen())
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.info),
            text = "Biz barada",
            onClick = {
                navigator.push(AboutScreen())
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.privacy),
            text = "Ulanyş düzgünleri",
            onClick = {
                navigator.push(PrivacyScreen())
            }
        ),
        ProfileItem(
            icon = painterResource(Res.drawable.exit),
            text = "Ulgamdan çykmak",
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
        ProfileToolbar(Modifier.fillMaxWidth())
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
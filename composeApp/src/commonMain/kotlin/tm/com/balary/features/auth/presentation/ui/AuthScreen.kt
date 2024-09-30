package tm.com.balary.features.auth.presentation.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.internal.BackHandler
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tm.com.balary.features.auth.presentation.viewmodel.AuthViewModel

class AuthScreen(
    private val isStart: Boolean = false
) : Screen {
    @Composable
    override fun Content() {
        Auth(Modifier.fillMaxSize(), isStart)
    }
}

@OptIn(InternalVoyagerApi::class, KoinExperimentalAPI::class)
@Composable
fun Auth(modifier: Modifier = Modifier, isStart: Boolean = false) {
    val navigator = LocalNavigator.currentOrThrow
    val index = rememberSaveable {
        mutableStateOf(0)
    }

    val authViewModel: AuthViewModel = koinViewModel()

    BackHandler(index.value != 0) {
        index.value = 0
    }
    Column(
        modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.background
        ).verticalScroll(rememberScrollState())
    ) {
        AppToolbar(Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        AnimatedContent(index.value) {
            if (it == 0) {
                SignIn(Modifier.fillMaxWidth(), isStart = isStart, onSignUp = {
                    index.value = 1
                }, authViewModel = authViewModel, onForgotPassword = {
                    navigator.push(ForgotPassword(authViewModel, isStart))
                })

            } else {
                SignUp(Modifier.fillMaxWidth(), isStart = isStart, authViewModel, onSignIn = {
                    index.value = 0
                })
            }
        }
        Spacer(Modifier.height(8.dp))
    }
}
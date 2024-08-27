package tm.com.balary.features.auth.presentation.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.internal.BackHandler

class AuthScreen : Screen {
    @Composable
    override fun Content() {
        Auth(Modifier.fillMaxSize())
    }
}

@OptIn(InternalVoyagerApi::class)
@Composable
fun Auth(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.currentOrThrow
    val index = rememberSaveable {
        mutableStateOf(0)
    }

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
                SignUp(Modifier.fillMaxWidth(), onSignIn = {
                    index.value = 1
                })
            } else {
                SignIn(Modifier.fillMaxWidth(), onSignUp = {
                    index.value = 0
                }, onForgotPassword = {
                    navigator.push(ForgotPassword())
                })
            }
        }
        Spacer(Modifier.height(8.dp))
    }
}
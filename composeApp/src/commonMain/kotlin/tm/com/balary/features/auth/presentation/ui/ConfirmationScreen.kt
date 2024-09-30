package tm.com.balary.features.auth.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dokar.sonner.Toast
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tm.com.balary.features.auth.data.entity.request.prettyPhone
import tm.com.balary.features.auth.presentation.viewmodel.AuthViewModel
import tm.com.balary.router.AppTabScreen
import tm.com.balary.state.LocalAppState
import tm.com.balary.state.LocalAuth
import tm.com.balary.state.LocalDarkMode

class ConfirmationScreen(
    private val phone: String,
    private val authViewModel: AuthViewModel,
    private val isChangePassword: Boolean = false,
    private val isFirst: Boolean = false
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val otp = rememberSaveable {
            mutableStateOf("")
        }
        val strings = LocalStrings.current
        val countdown = rememberSaveable {
            mutableStateOf(60)
        }
        val verifyOtpState = authViewModel.verifyOtpState.collectAsState()
        val verifyPasswordOtpState = authViewModel.verifyPasswordOtpState.collectAsState()
        val sendOtpState = authViewModel.sendOtpState.collectAsState()
        LaunchedEffect(verifyOtpState.value.sentKey) {
            countdown.value = 60
            while (countdown.value>=0) {
                countdown.value = countdown.value.minus(1)
                delay(500L)
            }
        }
        val isDark = LocalDarkMode.current
        val toaster = rememberToasterState()
        val authState = LocalAuth.current
        val appState = LocalAppState.current
        Toaster(
            state = toaster,
            darkTheme = isDark.value,
            richColors = true,
            alignment = Alignment.TopCenter
        )
        BackScreen(Modifier.fillMaxSize(), strings.confirmation, navHostController = rememberNavController(), onBack = {
            navigator.pop()
        }) {
            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                Column(
                    Modifier.fillMaxWidth().background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(20.dp)
                    ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        strings.sentCode.replace("{phone_number}",phone.prettyPhone()),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W700,
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    OtpTextField(modifier = Modifier.fillMaxWidth(),
                        otpText = otp.value,
                        otpCount = 5,
                        onOtpTextChange = { value, _ ->
                            otp.value = value
                        })



                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if(sendOtpState.value.loading) {
                            CircularProgressIndicator(Modifier.size(30.dp))
                        } else {
                            Text(
                                if(countdown.value>0) "00:${countdown.value}" else strings.sentAgain,
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.W700
                                ),
                                modifier = Modifier.clickable {
                                    if(countdown.value<=0) {
                                        authViewModel.sentOtp(
                                            phone = phone,
                                            onError = {message->
                                                message?.let {
                                                    toaster.show(message, type = ToastType.Error)
                                                }
                                            },
                                            onSuccess = {
                                                authViewModel.changeVerifyKey()
                                            }
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
                Spacer(
                    Modifier.weight(1f)
                )

                Column(
                    Modifier.fillMaxWidth().background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            if(isChangePassword.not()) {
                                authViewModel.verifyOtp(
                                    otp.value,
                                    onError = {message->
                                        message?.let {
                                            toaster.show(
                                                message,
                                                type = ToastType.Error
                                            )
                                        }
                                    },
                                    onSuccess = {
                                        if(isFirst) {
                                            appState.value = appState.value.copy(
                                                isFirst = false
                                            )
                                        }
                                        authState.value = authState.value.copy(
                                            logged = true
                                        )
                                        navigator.replaceAll(AppTabScreen())
                                    }
                                )
                            } else {
                                authViewModel.verifyPasswordOtp(
                                    otp.value,
                                    onError = {message->
                                        message?.let {
                                            toaster.show(
                                                message,
                                                type = ToastType.Error
                                            )
                                        }
                                    },
                                    onSuccess = {
                                        navigator.push(ChangePasswordScreen(authViewModel, isFirst))
                                    }
                                )
                            }
                        },
                        enabled = otp.value.length == 5
                    ) {
                        if(verifyOtpState.value.loading || verifyPasswordOtpState.value.loading) {
                            CircularProgressIndicator(Modifier.size(30.dp), color = MaterialTheme.colorScheme.onPrimary)
                        } else {
                            Text(
                                strings.accept,
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.W700
                                )
                            )
                        }
                    }

                    Spacer(Modifier.imePadding().padding(WindowInsets.ime.asPaddingValues()))
                }
            }
        }
    }
}


@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 4,
    onOtpTextChange: (String, Boolean) -> Unit
) {

    BasicTextField(
        modifier = modifier,
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(otpCount) { index ->
                    CharView(
                        modifier = Modifier.weight(1f),
                        index = index,
                        text = otpText
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}

@Composable
private fun CharView(
    modifier: Modifier = Modifier,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    index: Int,
    text: String,
) {
    val isFocused = text.length >= index
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Box(
        modifier
            .height(65.dp)
            .border(
                1.dp, when {
                    isFocused -> MaterialTheme.colorScheme.primary
                    else -> borderColor
                }, RoundedCornerShape(8.dp)
            )
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            text = char,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.W700,
                fontSize = 26.sp
            ),
            color = if (isFocused) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.outline
            },
            textAlign = TextAlign.Center
        )
    }

}
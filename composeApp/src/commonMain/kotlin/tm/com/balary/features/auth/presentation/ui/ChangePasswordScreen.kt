package tm.com.balary.features.auth.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.eye
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.internal.BackHandler
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.auth.presentation.viewmodel.AuthViewModel
import tm.com.balary.router.AppTabScreen
import tm.com.balary.state.LocalAppState
import tm.com.balary.state.LocalAuth
import tm.com.balary.state.LocalDarkMode

class ChangePasswordScreen(
    private val authViewModel: AuthViewModel,
    private val isFirst: Boolean = false
) : Screen {
    @Composable
    override fun Content() {
        ChangePassword(Modifier.fillMaxSize(), authViewModel, isFirst)
    }
}

@OptIn(InternalVoyagerApi::class)
@Composable
fun ChangePassword(modifier: Modifier = Modifier, authViewModel: AuthViewModel, isFirst: Boolean) {
    val navigator = LocalNavigator.currentOrThrow
    val index = rememberSaveable {
        mutableStateOf(0)
    }

    val strings = LocalStrings.current

    val formState = authViewModel.changePasswordFormState

    val changePasswordState = authViewModel.changePasswordState.collectAsState()

    BackHandler(index.value != 0) {
        index.value = 0
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
    Column(
        modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.background
        ).verticalScroll(rememberScrollState())
    ) {
        AppToolbar(Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        val show = rememberSaveable {
            mutableStateOf(false)
        }
        Column(
            modifier.fillMaxWidth().background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                strings.changePassword,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.W900,
                    fontSize = 24.sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )




            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = formState.value.password,
                onValueChange = {
                    authViewModel.onPasswordChangePassword(it)
                },
                isError = formState.value.passwordError,
                supportingText = {
                    if(formState.value.passwordError) {
                        Text(
                            strings.passwordError,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                maxLines = 1,
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    errorTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface
                ),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W700
                ),
                trailingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.eye),
                        contentDescription = "password",
                        tint = if (show.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(24.dp).clip(CircleShape).clickable {
                            show.value = show.value.not()
                        }
                    )
                },
                visualTransformation = if (show.value) VisualTransformation.None else PasswordVisualTransformation(),
                label = {
                    Text(strings.password)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (show.value) KeyboardType.Text else KeyboardType.Password,
                    imeAction = ImeAction.Next
                )
            )


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = formState.value.confirmPassword,
                onValueChange = {
                    authViewModel.onPasswordChangeConfirmPassword(it)
                },
                isError = formState.value.confirmPasswordError || formState.value.isPasswordMatch.not(),
                supportingText = {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        if(formState.value.confirmPasswordError) {
                            Text(
                                strings.confirmPasswordError,
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        if(formState.value.isPasswordMatch.not()) {
                            Text(
                                strings.passwordNotMatch,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                },
                maxLines = 1,
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    errorTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface
                ),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W700
                ),
                trailingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.eye),
                        contentDescription = "password",
                        tint = if (show.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(24.dp).clip(CircleShape).clickable {
                            show.value = show.value.not()
                        }
                    )
                },
                visualTransformation = if (show.value) VisualTransformation.None else PasswordVisualTransformation(),
                label = {
                    Text(strings.confirmPassword)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (show.value) KeyboardType.Text else KeyboardType.Password,
                    imeAction = ImeAction.Next
                )
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    val isValid = authViewModel.validateChangePassword()
                    if(isValid) {
                        authViewModel.changePassword(
                            onError = { message->
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
                    }
                }
            ) {
                if(changePasswordState.value.loading) {
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


        }
            Spacer(Modifier.height(8.dp))
    }
}
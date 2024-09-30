package tm.com.balary.features.auth.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dokar.sonner.Toast
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.auth.presentation.viewmodel.AuthViewModel
import tm.com.balary.router.AppTabScreen
import tm.com.balary.state.LocalAppState
import tm.com.balary.state.LocalAuth
import tm.com.balary.state.LocalDarkMode

@Composable
fun SignIn(
    modifier: Modifier = Modifier,
    isStart: Boolean = false,
    onSignUp: () -> Unit,
    authViewModel: AuthViewModel,
    onForgotPassword: () -> Unit,
) {
    val navigator = LocalNavigator.currentOrThrow
    val show = rememberSaveable {
        mutableStateOf(false)
    }
    val appState = LocalAppState.current
    val strings = LocalStrings.current
    val signInState = authViewModel.loginFormState
    val loginState = authViewModel.signInState.collectAsState()
    val isDark = LocalDarkMode.current
    val toaster = rememberToasterState()
    val authState = LocalAuth.current
    Toaster(
        state = toaster,
        darkTheme = isDark.value,
        richColors = true,
        alignment = Alignment.TopCenter
    )
    Column(
        modifier.fillMaxWidth().background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(20.dp)
        ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            strings.signIn,
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
            value = signInState.value.phone,
            onValueChange = {
                authViewModel.onLoginPhoneNumberChange(it)
            },
            isError = signInState.value.phoneError,
            supportingText = {
                if (signInState.value.phoneError) {
                    Text(
                        strings.phoneNumberError,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = {
                if (signInState.value.phoneError) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = "error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            prefix = {
                Text(
                    "+993", style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700
                    )
                )
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
            label = {
                Text(strings.phoneNumber)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        )


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signInState.value.password,
            onValueChange = {
                authViewModel.onLoginPasswordChange(it)
            },
            isError = signInState.value.passwordError,
            supportingText = {
                if (signInState.value.passwordError) {
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

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                strings.forgotPassword,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W700
                ),
                modifier = Modifier.clickable {
                    onForgotPassword()
                }
            )
        }



        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                val isValid = authViewModel.validateLoginForm()
                if (isValid) {
                    authViewModel.login(
                        onError = { message ->
                            message?.let {
                                toaster.show(
                                    message,
                                    type = ToastType.Error
                                )
                            }
                        },
                        onSuccess = {
                            if(isStart) {
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
            if (loginState.value.loading) {
                CircularProgressIndicator(
                    Modifier.size(30.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    strings.signIn,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W700
                    )
                )
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                strings.notHaveAccount,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W700
                )
            )
            Spacer(Modifier.width(8.dp))

            Text(
                strings.signUp2,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W700
                ),
                modifier = Modifier.clickable {
                    onSignUp()
                }
            )
        }
    }
}

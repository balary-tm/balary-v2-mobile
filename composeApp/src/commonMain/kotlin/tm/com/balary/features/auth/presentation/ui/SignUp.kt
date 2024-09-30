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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.eye
import balary.composeapp.generated.resources.personfilled
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dokar.sonner.Toast
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import tm.com.balary.features.auth.presentation.viewmodel.AuthViewModel
import tm.com.balary.router.AppTabScreen
import tm.com.balary.state.LocalAppState
import tm.com.balary.state.LocalDarkMode
import tm.com.balary.ui.AppCheckBox
import tm.com.balary.ui.ImageLoader

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SignUp(
    modifier: Modifier = Modifier,
    isStart: Boolean = false,
    authViewModel: AuthViewModel,
    onSignIn: () -> Unit
) {
    val fullName = rememberSaveable {
        mutableStateOf("")
    }
    val navigator = LocalNavigator.currentOrThrow
    val show = rememberSaveable {
        mutableStateOf(false)
    }
    val appState = LocalAppState.current
    val strings = LocalStrings.current
    val signUpState = authViewModel.signUpState
    val sentOtpState = authViewModel.sendOtpState.collectAsState()
    val isDark = LocalDarkMode.current
    val toaster = rememberToasterState()
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
            strings.signUp2,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.W900,
                fontSize = 24.sp
            ),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        UserAvatar()

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpState.value.fullName,
            onValueChange = {
                authViewModel.onFullNameChange(it)
            },
            isError = signUpState.value.fullNameError,
            trailingIcon = {
                if (signUpState.value.fullNameError) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = "error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (signUpState.value.fullNameError) {
                    Text(
                        strings.fullNameError,
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
            label = {
                Text(strings.fullName)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = signUpState.value.phone,
            onValueChange = {
                authViewModel.onPhoneNumberChange(it)
            },
            isError = signUpState.value.phoneError,
            supportingText = {
                if (signUpState.value.phoneError) {
                    Text(
                        strings.phoneNumberError,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = {
                if (signUpState.value.phoneError) {
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
            value = signUpState.value.password,
            onValueChange = {
                authViewModel.onPasswordChange(it)
            },
            isError = signUpState.value.passwordError,
            supportingText = {
                if (signUpState.value.passwordError) {
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
            value = signUpState.value.confirmPassword,
            onValueChange = {
                authViewModel.onConfirmPasswordChange(it)
            },
            isError = signUpState.value.confirmPasswordError || signUpState.value.isPasswordMatch.not(),
            supportingText = {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (signUpState.value.confirmPasswordError) {
                        Text(
                            strings.confirmPasswordError,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    if (signUpState.value.isPasswordMatch.not()) {
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

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AppCheckBox(
                checked = signUpState.value.accept,
                onChange = {
                    authViewModel.onAcceptChange(it)
                }
            )
            Text(
                strings.acceptUserTerms,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W700
                ),
                color = if (signUpState.value.acceptError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.clickable {
                },
                textDecoration = TextDecoration.Underline
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                val isValid = authViewModel.validateSignUpForm()
                if (isValid) {
                    authViewModel.sentOtp(
                        phone = signUpState.value.phone,
                        onError = { message ->
                            message?.let {
                                toaster.show(
                                    message,
                                    type = ToastType.Error
                                )
                            }
                        },
                        onSuccess = {
                            navigator.push(
                                ConfirmationScreen(
                                    signUpState.value.phone,
                                    authViewModel,
                                    isFirst = isStart
                                )
                            )
                        }
                    )

                }
            }
        ) {
            if (sentOtpState.value.loading) {
                CircularProgressIndicator(
                    Modifier.size(30.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    strings.signUp,
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
                strings.haveAccount,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W700
                )
            )
            Spacer(Modifier.width(8.dp))

            Text(
                strings.signIn,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W700
                ),
                modifier = Modifier.clickable {
                    onSignIn()
                }
            )
        }
    }
}

@Composable
fun UserAvatar(modifier: Modifier = Modifier) {
    ImageLoader(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = CircleShape
        ).clip(CircleShape).size(40.dp).padding(6.dp),
        url = "",
        placeholder = painterResource(Res.drawable.personfilled)
    )
}
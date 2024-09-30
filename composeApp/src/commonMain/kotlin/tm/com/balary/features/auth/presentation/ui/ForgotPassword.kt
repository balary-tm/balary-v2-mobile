package tm.com.balary.features.auth.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import tm.com.balary.features.auth.data.entity.request.prettyPhone
import tm.com.balary.features.auth.presentation.viewmodel.AuthViewModel
import tm.com.balary.state.LocalDarkMode

class ForgotPassword(
    private val authViewModel: AuthViewModel,
    private val isStart: Boolean = false
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val strings = LocalStrings.current
        val formState = authViewModel.changePasswordFormState
        val isDark = LocalDarkMode.current
        val toaster = rememberToasterState()
        val sentCodeState = authViewModel.sendOtpState.collectAsState()
        Toaster(
            state = toaster,
            darkTheme = isDark.value,
            richColors = true,
            alignment = Alignment.TopCenter
        )
        BackScreen(Modifier.fillMaxSize(), strings.editPassword, navHostController = rememberNavController(), onBack = {
            navigator.pop()
        }) {
            Column(
                Modifier.fillMaxWidth().background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                ).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    strings.editPassword2,
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
                    value = formState.value.phone,
                    onValueChange = {
                        authViewModel.onPasswordChangePhone(it)
                    },
                    isError = formState.value.phoneError,
                    supportingText = {
                        if(formState.value.phoneError) {
                            Text(
                                strings.phoneNumberError,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    trailingIcon = {
                        if (formState.value.phoneError) {
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

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        val isValid = authViewModel.validateChangePasswordPhone()
                        if (isValid) {
                            authViewModel.sentOtp(
                                formState.value.phone,
                                onError = { message->
                                    message?.let {
                                        toaster.show(
                                            message,
                                            type = ToastType.Error
                                        )
                                    }
                                },
                                onSuccess = {
                                    navigator.push(ConfirmationScreen(
                                        formState.value.phone,
                                        authViewModel,
                                        isChangePassword = true,
                                        isFirst = isStart
                                    ))
                                }
                            )
                        }
                    }
                ) {
                    if(sentCodeState.value.loading) {
                        CircularProgressIndicator(Modifier.size(30.dp), color = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        Text(
                            strings.sendCode,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W700
                            )
                        )
                    }
                }
            }
        }
    }
}
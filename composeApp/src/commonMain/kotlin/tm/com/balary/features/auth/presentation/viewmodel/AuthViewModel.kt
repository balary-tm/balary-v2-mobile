package tm.com.balary.features.auth.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tm.com.balary.core.Resource
import tm.com.balary.features.auth.data.entity.request.ChangePasswordBody
import tm.com.balary.features.auth.data.entity.request.LoginBody
import tm.com.balary.features.auth.data.entity.request.SentOtpBody
import tm.com.balary.features.auth.data.entity.request.VerifyOtpBody
import tm.com.balary.features.auth.data.entity.request.VerifyOtpForPassword
import tm.com.balary.features.auth.data.entity.request.prettyPhone
import tm.com.balary.features.auth.domain.usecase.AuthUseCase
import tm.com.balary.features.auth.presentation.state.ChangePasswordFormState
import tm.com.balary.features.auth.presentation.state.ChangePasswordState
import tm.com.balary.features.auth.presentation.state.LoginFormState
import tm.com.balary.features.auth.presentation.state.SendOtpState
import tm.com.balary.features.auth.presentation.state.SignInState
import tm.com.balary.features.auth.presentation.state.SignUpState
import tm.com.balary.features.auth.presentation.state.VerifyOtpState
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class AuthViewModel(
    private val useCase: AuthUseCase
) : ViewModel() {
    private val _sentOtpState = MutableStateFlow(SendOtpState())
    val sendOtpState = _sentOtpState.asStateFlow()

    private val _verifyOtpState = MutableStateFlow(VerifyOtpState())
    val verifyOtpState = _verifyOtpState.asStateFlow()

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    private val _verifyPasswordOtpState = MutableStateFlow(VerifyOtpState())
    val verifyPasswordOtpState = _verifyPasswordOtpState.asStateFlow()

    private val _changePasswordState = MutableStateFlow(ChangePasswordState())
    val changePasswordState = _changePasswordState.asStateFlow()

    /* Sign up section start */

    val signUpState = mutableStateOf(SignUpState())

    fun validateSignUpForm(): Boolean {
        val it = signUpState.value
        signUpState.value = signUpState.value.copy(
            fullNameError = it.validateFullName(),
            phoneError = it.validatePhone(),
            passwordError = it.validatePassword(),
            confirmPasswordError = it.validateConfirmPassword(),
            isPasswordMatch = it.password == it.confirmPassword,
            acceptError = it.accept.not()
        )

        return (signUpState.value.fullNameError.not()
                && signUpState.value.phoneError.not()
                && signUpState.value.passwordError.not()
                && signUpState.value.confirmPasswordError.not()
                && signUpState.value.isPasswordMatch
                && signUpState.value.accept)
    }

    fun onFullNameChange(value: String) {
        signUpState.value = signUpState.value.copy(
            fullName = value,
            fullNameError = signUpState.value.validateFullName()
        )
    }

    fun onPhoneNumberChange(value: String) {
        signUpState.value = signUpState.value.copy(
            phone = value,
            phoneError = signUpState.value.validatePhone()
        )
    }

    fun onPasswordChange(value: String) {
        signUpState.value = signUpState.value.copy(
            password = value,
            passwordError = signUpState.value.validatePassword()
        )
    }

    fun onConfirmPasswordChange(value: String) {
        signUpState.value = signUpState.value.copy(
            confirmPassword = value,
            isPasswordMatch = value == signUpState.value.password,
            confirmPasswordError =  signUpState.value.validateConfirmPassword()
        )
    }

    fun onAcceptChange(value: Boolean) {
        signUpState.value = signUpState.value.copy(
            accept = value,
            acceptError = value.not()
        )
    }

    /* Sign up section end */

    val loginFormState = mutableStateOf(LoginFormState())

    fun validateLoginForm(): Boolean {
        val it = loginFormState.value
        loginFormState.value = loginFormState.value.copy(
            phoneError = it.validatePhone(),
            passwordError = it.validatePassword(),
        )

        return (loginFormState.value.phoneError.not()
                && loginFormState.value.passwordError.not())
    }

    fun onLoginPhoneNumberChange(value: String) {
        loginFormState.value = loginFormState.value.copy(
            phone = value,
            phoneError = loginFormState.value.validatePhone()
        )
    }

    fun onLoginPasswordChange(value: String) {
        loginFormState.value = loginFormState.value.copy(
            password = value,
            passwordError = loginFormState.value.validatePassword()
        )
    }

    fun login(onError: (String?) -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            useCase.signIn(
                body = LoginBody(
                    phone = loginFormState.value.phone.prettyPhone(),
                    password = loginFormState.value.password
                )
            ).onEach { result->
                when(result) {
                    is Resource.Error -> {
                        onError(result.message)
                        _signInState.value = _signInState.value.copy(
                            loading = false,
                            error = result.message,
                            data = result.data
                        )
                    }
                    is Resource.Loading -> {
                        _signInState.value = _signInState.value.copy(
                            loading = true,
                            error = result.message,
                            data = result.data
                        )
                    }
                    is Resource.Success -> {
                        onSuccess()
                        _signInState.value = _signInState.value.copy(
                            loading = false,
                            error = result.message,
                            data = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun sentOtp(phone: String, onError: (String?)-> Unit, onSuccess: ()-> Unit) {
        viewModelScope.launch {
            useCase.sendOtp(body = SentOtpBody(phone = phone.prettyPhone())).onEach { result->
                when(result) {
                    is Resource.Error -> {
                        onError(result.message)
                        _sentOtpState.value = _sentOtpState.value.copy(
                            loading = false,
                            error = result.message,
                            phone = phone
                        )
                    }
                    is Resource.Loading -> {
                        _sentOtpState.value = _sentOtpState.value.copy(
                            loading = true,
                            error = result.message,
                            phone = phone
                        )
                    }
                    is Resource.Success -> {
                        onSuccess()
                        _sentOtpState.value = _sentOtpState.value.copy(
                            loading = false,
                            error = result.message,
                            phone = phone
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun changeVerifyKey() {
        _verifyOtpState.value = _verifyOtpState.value.copy(
            sentKey = Uuid.random().toHexString()
        )
    }

    fun verifyOtp(code: String, onError: (String?) -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            useCase.verifyOtp(
                body = VerifyOtpBody(
                    fullname = signUpState.value.fullName,
                    phone = signUpState.value.phone.prettyPhone(),
                    password = signUpState.value.password,
                    otp_code = code
                )
            ).onEach { result->
                when(result) {
                    is Resource.Error -> {
                        onError(result.message)
                        _verifyOtpState.value = _verifyOtpState.value.copy(
                            loading = false,
                            error = result.message,
                            data = result.data
                        )
                    }
                    is Resource.Loading -> {
                        _verifyOtpState.value = _verifyOtpState.value.copy(
                            loading = true,
                            error = result.message,
                            data = result.data
                        )
                    }
                    is Resource.Success -> {
                        onSuccess()
                        _verifyOtpState.value = _verifyOtpState.value.copy(
                            loading = false,
                            error = result.message,
                            data = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    val changePasswordFormState = mutableStateOf(ChangePasswordFormState())

    fun verifyPasswordOtp(code: String, onError: (String?) -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            useCase.verifyOtpForPassword(
                data = VerifyOtpForPassword(
                    phone = changePasswordFormState.value.phone.prettyPhone(),
                    otp_code = code
                )
            ).onEach { result->
                when(result) {
                    is Resource.Error -> {
                        onError(result.message)
                        _verifyPasswordOtpState.value = _verifyPasswordOtpState.value.copy(
                            loading = false,
                            error = result.message,
                            data = result.data
                        )
                    }
                    is Resource.Loading -> {
                        _verifyPasswordOtpState.value = _verifyPasswordOtpState.value.copy(
                            loading = true,
                            error = result.message,
                            data = result.data
                        )
                    }
                    is Resource.Success -> {
                        onSuccess()
                        _verifyPasswordOtpState.value = _verifyPasswordOtpState.value.copy(
                            loading = false,
                            error = result.message,
                            data = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun validateChangePasswordPhone(): Boolean {
        val it = changePasswordFormState.value
        changePasswordFormState.value = changePasswordFormState.value.copy(
            phoneError = it.validatePhone()
        )

        return it.validatePhone().not()
    }

    fun validateChangePassword(): Boolean {
        val it = changePasswordFormState.value
        changePasswordFormState.value = changePasswordFormState.value.copy(
            phoneError = it.validatePhone(),
            passwordError = it.validatePassword(),
            confirmPasswordError = it.validateConfirmPassword(),
            isPasswordMatch = it.password == it.confirmPassword
        )

        return (changePasswordFormState.value.phoneError.not()
                && changePasswordFormState.value.passwordError.not()
                && changePasswordFormState.value.confirmPasswordError.not()
                && changePasswordFormState.value.isPasswordMatch)
    }

    fun onPasswordChangePhone(value: String) {
        changePasswordFormState.value = changePasswordFormState.value.copy(
            phone = value,
            phoneError = value.length<=7
        )
    }

    fun onPasswordChangePassword(value: String) {
        val it = changePasswordFormState.value
        changePasswordFormState.value = changePasswordFormState.value.copy(
            password = value,
            passwordError = it.validatePassword(),
            isPasswordMatch = it.confirmPassword == value
        )
    }

    fun onPasswordChangeConfirmPassword(value: String) {
        val it = changePasswordFormState.value
        changePasswordFormState.value = changePasswordFormState.value.copy(
            confirmPassword = value,
            confirmPasswordError = it.validatePassword(),
            isPasswordMatch = it.password == value
        )
    }

    fun changePassword(
        onError: (String?) -> Unit,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            useCase.changePassword(
                data = ChangePasswordBody(
                    new_password = changePasswordFormState.value.password,
                    phone = changePasswordFormState.value.phone.prettyPhone()
                )
            ).onEach { result->
                when(result) {
                    is Resource.Error -> {
                        onError(result.message)
                        _changePasswordState.value = _changePasswordState.value.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _changePasswordState.value = _changePasswordState.value.copy(
                            loading = true,
                            error = result.message
                        )
                    }
                    is Resource.Success -> {
                        onSuccess()
                        _changePasswordState.value = _changePasswordState.value.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                }
            }.launchIn(this)
        }
    }


}
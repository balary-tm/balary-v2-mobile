package tm.com.balary.features.auth.presentation.state

data class ChangePasswordFormState(
    val phone: String = "",
    val phoneError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false,
    val confirmPassword: String = "",
    val confirmPasswordError: Boolean = false,
    val isPasswordMatch: Boolean = true,
) {
    fun validatePhone(): Boolean {
        return phone.isEmpty() || phone.length <= 7
    }

    fun validatePassword(): Boolean {
        return password.isEmpty() || password.length < 2
    }

    fun validateConfirmPassword(): Boolean {
        return confirmPassword.isEmpty() && confirmPassword.length < 2
    }
}
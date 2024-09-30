package tm.com.balary.features.auth.presentation.state

data class LoginFormState(
    val phone: String = "",
    val phoneError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false,
) {
    fun validatePhone(): Boolean {
        return phone.isEmpty() || phone.length < 7
    }

    fun validatePassword(): Boolean {
        return password.isEmpty() || password.length < 2
    }
}

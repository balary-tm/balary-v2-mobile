package tm.com.balary.features.auth.presentation.state


data class ChangePasswordState(
    val loading: Boolean = false,
    val error: String? = null
)

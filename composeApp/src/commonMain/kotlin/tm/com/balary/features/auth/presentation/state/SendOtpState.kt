package tm.com.balary.features.auth.presentation.state

data class SendOtpState(
    val loading: Boolean = false,
    val error: String? = null,
    val phone: String = ""
)

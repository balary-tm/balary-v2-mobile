package tm.com.balary.features.auth.data.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpForPassword(
    val otp_code: String,
    val phone: String
)
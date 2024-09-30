package tm.com.balary.features.auth.data.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpBody(
    val fullname: String,
    val otp_code: String,
    val password: String,
    val phone: String
)
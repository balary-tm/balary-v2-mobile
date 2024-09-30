package tm.com.balary.features.auth.data.entity.response

import kotlinx.serialization.Serializable
import tm.com.balary.features.auth.domain.model.AuthModel

@Serializable
data class VerifyOtpResponse(
    val access_token: String
) {
    fun toUIEntity(): AuthModel {
        return AuthModel(access_token)
    }
}

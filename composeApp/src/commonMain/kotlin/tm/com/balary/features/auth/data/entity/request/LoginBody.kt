package tm.com.balary.features.auth.data.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
    val password: String,
    val phone: String
)
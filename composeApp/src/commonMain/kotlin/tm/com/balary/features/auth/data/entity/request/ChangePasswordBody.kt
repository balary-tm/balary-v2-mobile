package tm.com.balary.features.auth.data.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordBody(
    val new_password: String,
    val phone: String
)
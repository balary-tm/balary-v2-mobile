package tm.com.balary.features.auth.data.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class SentOtpBody(
    val phone: String
)

fun String.prettyPhone(): String {
    return "+993"+this.replace("+993","").trim()
}
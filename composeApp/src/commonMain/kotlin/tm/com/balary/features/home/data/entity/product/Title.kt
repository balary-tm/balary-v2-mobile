package tm.com.balary.features.home.data.entity.product

import kotlinx.serialization.Serializable

@Serializable
data class Title(
    val ru: String,
    val tm: String
)
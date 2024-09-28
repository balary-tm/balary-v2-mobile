package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable

@Serializable
data class Val(
    val id: Int,
    val value_ru: String,
    val value_tm: String
)
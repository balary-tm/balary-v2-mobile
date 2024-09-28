package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable

@Serializable
data class Attribute(
    val id: Int,
    val name_ru: String,
    val name_tm: String,
    val vals: List<Val>
)
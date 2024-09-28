package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable

@Serializable
data class Value(
    val extra: String? = null,
    val image: String? = null,
    val value_ru: String? = null,
    val value_tm: String? = null,
    val variant_id: Int? = null
)
package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable

@Serializable
data class OtherVariantItem(
    val id: Int,
    val key: String? = null,
    val name_ru: String? = null,
    val name_tm: String? = null,
    val values: List<Value>? = null
)
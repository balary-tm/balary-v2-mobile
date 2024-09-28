package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable

@Serializable
data class SingleProductResponse(
    val attributes: List<Attribute>? = null,
    val brand: Brand? = null,
    val category: Category? = null,
    val code: String? = null,
    val description_ru: String? = null,
    val description_tm: String? = null,
    val disc_remaining_time: Long? = null,
    val discount: Double? = null,
    val discount_price: Double? = null,
    val id: Int,
    val medias: List<ProductMedia>? = null,
    val price: Double? = null,
    val properties: List<Property>? = null,
    val sku: String? = null,
    val title_ru: String? = null,
    val title_tm: String? = null,
    val label: String? = null,
    val similar_products: SimilarProduct? = null,
    val other_variants: List<OtherVariantItem>? = null
)
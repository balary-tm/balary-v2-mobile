package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable
import tm.com.balary.features.basket.data.local.BasketLocalEntity

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
) {
    fun toBasketEntity(count: Int): BasketLocalEntity {
        return BasketLocalEntity(
            discount = discount?:0.0,
            price = price?:0.0,
            count = count,
            title_tm = title_tm?:"",
            title_ru = title_ru?:"",
            id = id,
            description_tm = description_tm?:"",
            description_ru = description_ru?:"",
            disc_remaining_time = disc_remaining_time?:0L,
            priceWithDiscount = discount_price?:0.0,
            thumbnail = if(medias.isNullOrEmpty()) "" else medias[0].thumbnail
        )
    }
}
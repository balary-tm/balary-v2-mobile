package tm.com.balary.features.product.domain.model

import kotlinx.serialization.Serializable
import tm.com.balary.features.basket.data.local.BasketLocalEntity

@Serializable
data class ProductModel(
    val blurhash: String,
    val disc_remaining_time: Double,
    val discount: Double,
    val discount_price: Double,
    val id: Int,
    val image: String,
    val price: Double,
    val title_tm: String,
    val title_ru: String,
    val description_tm: String,
    val description_ru: String,
    val category_id: Int? = null,
) {
    fun toBasketEntity(count: Int): BasketLocalEntity {
        return BasketLocalEntity(
            discount = discount,
            price = price,
            count = count,
            title_tm = title_tm,
            title_ru = title_ru,
            id = id,
            description_tm = description_tm,
            description_ru = description_ru,
            disc_remaining_time = disc_remaining_time.toLong(),
            priceWithDiscount = discount_price,
            thumbnail = image
        )
    }
}

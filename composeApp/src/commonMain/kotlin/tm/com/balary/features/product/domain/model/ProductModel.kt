package tm.com.balary.features.product.domain.model

import tm.com.balary.features.home.data.entity.product.Title

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
)

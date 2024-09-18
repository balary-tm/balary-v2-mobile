package tm.com.balary.features.home.data.entity.product

import kotlinx.serialization.Serializable
import tm.com.balary.features.product.domain.model.ProductModel

@Serializable
data class ProductEntity(
    val blurhash: String?,
    val disc_remaining_time: Double?,
    val discount: Double?,
    val discount_price: Double?,
    val id: Int,
    val image: String?,
    val price: Double?,
    val title: Title?
) {
    fun toUIEntity(): ProductModel {
        return ProductModel(
            blurhash = blurhash?:"",
            disc_remaining_time = disc_remaining_time?:0.0,
            discount = discount?:0.0,
            discount_price = discount_price?:0.0,
            id = id,
            image = image?:"",
            price = price?:0.0,
            title_ru = title?.ru?:"",
            title_tm = title?.tm?:""
        )
    }
}
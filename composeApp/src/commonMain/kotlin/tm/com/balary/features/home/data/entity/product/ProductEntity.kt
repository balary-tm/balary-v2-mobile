package tm.com.balary.features.home.data.entity.product

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.data.datasource.local.ProductLocalEntity
import tm.com.balary.features.product.domain.model.ProductModel


@Serializable
data class ProductEntity(
    val blurhash: String?,
    val disc_remaining_time: Double?,
    val discount: Double?,
    val discount_price: Double?,
    val id: Int,
    val thumbnail: String?,
    val price: Double?,
    val title: Title?,
    val description: Title?,
    val category_id: Int? = null,
) {
    fun toCacheEntity(type: String, categoryId: Int = -1): ProductLocalEntity {
        return ProductLocalEntity(
            blurhash = blurhash?:"",
            disc_remaining_time = disc_remaining_time?:0.0,
            discount = discount?:0.0,
            discount_price = discount_price?:0.0,
            id = id,
            image = thumbnail?:"",
            price = price?:0.0,
            title_ru = title?.ru?:"",
            title_tm = title?.tm?:"",
            description_ru = description?.ru?:"",
            description_tm = description?.tm?:"",
            type = type,
            categoryId = categoryId
        )
    }
    fun toUIEntity(): ProductModel {
        return ProductModel(
            blurhash = blurhash?:"",
            disc_remaining_time = disc_remaining_time?:0.0,
            discount = discount?:0.0,
            discount_price = discount_price?:0.0,
            id = id,
            image = thumbnail?:"",
            price = price?:0.0,
            title_ru = title?.ru?:"",
            title_tm = title?.tm?:"",
            description_ru = description?.ru?:"",
            description_tm = description?.tm?:"",
            category_id = category_id
        )
    }
}
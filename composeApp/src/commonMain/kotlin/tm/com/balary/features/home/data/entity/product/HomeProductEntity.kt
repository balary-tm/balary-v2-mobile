package tm.com.balary.features.home.data.entity.product

import kotlinx.serialization.Serializable

@Serializable
data class HomeProductEntity(
    val discount_images: List<DiscountImage>,
    val discount_products: List<ProductEntity>,
    val new_products: List<ProductEntity>
)
package tm.com.balary.features.home.domain.model

import kotlinx.serialization.Serializable
import tm.com.balary.features.product.domain.model.ProductModel

@Serializable
enum class SlideType{
    IMAGE,
    VIDEO
}

@Serializable
data class HomeCategory(
    val id: Int,
    val products: List<ProductModel>,
    val slide: String,
    val title_ru: String,
    val title_tm: String,
    val slideType: SlideType
)

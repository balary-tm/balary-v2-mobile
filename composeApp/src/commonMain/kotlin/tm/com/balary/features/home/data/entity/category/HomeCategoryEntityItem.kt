package tm.com.balary.features.home.data.entity.category

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.data.entity.product.ProductEntity
import tm.com.balary.features.home.data.entity.product.Title
import tm.com.balary.features.home.domain.model.HomeCategory

@Serializable
data class HomeCategoryEntityItem(
    val id: Int,
    val products: List<ProductEntity>?,
    val slide: String?,
    val title: Title?,
    val title_ru: String?,
    val title_tm: String?
) {
    fun toUIEntity(): HomeCategory {
        return HomeCategory(
            id = id,
            products = products?.map { it.toUIEntity() }?: emptyList(),
            slide = slide?:"",
            title_tm = title_tm?:"",
            title_ru = title_ru?:""
        )
    }
}
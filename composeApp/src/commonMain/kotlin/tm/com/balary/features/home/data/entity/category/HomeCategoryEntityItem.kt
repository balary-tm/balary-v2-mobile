package tm.com.balary.features.home.data.entity.category

import kotlinx.serialization.Serializable
import tm.com.balary.core.Constant
import tm.com.balary.features.home.data.datasource.local.HomeCategoryLocalEntity
import tm.com.balary.features.home.data.entity.product.ProductEntity
import tm.com.balary.features.home.data.entity.product.Title
import tm.com.balary.features.home.domain.model.HomeCategory
import tm.com.balary.features.home.domain.model.SlideType

@Serializable
data class HomeCategoryEntityItem(
    val id: Int,
    val products: List<ProductEntity>?,
    val slide: String?,
    val title: Title?,
    val title_ru: String?,
    val title_tm: String?
) {
    private fun checkSlideType(): SlideType {
        try {
            slide?.let { url->
                val isVideo = Constant.VIDEO_EXTENSIONS.any { url.lowercase().endsWith(it.lowercase()) }
                if(isVideo)
                    return SlideType.VIDEO
                return SlideType.IMAGE
            }
            return SlideType.IMAGE
        } catch (_: Exception) {
            return SlideType.IMAGE
        }
    }

    fun toCacheEntity(): HomeCategoryLocalEntity {
        return HomeCategoryLocalEntity(
            id = id,
            slide = slide?:"",
            title_tm = title_tm?:"",
            title_ru = title_ru?:"",
        )
    }

    fun toUIEntity(): HomeCategory {
        return HomeCategory(
            id = id,
            products = products?.map { it.toUIEntity() }?: emptyList(),
            slide = slide?:"",
            title_tm = title_tm?:"",
            title_ru = title_ru?:"",
            slideType = checkSlideType()
        )
    }
}
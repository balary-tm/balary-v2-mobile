package tm.com.balary.features.home.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import tm.com.balary.core.Constant
import tm.com.balary.features.home.domain.model.HomeCategory
import tm.com.balary.features.home.domain.model.SlideType
import tm.com.balary.features.product.domain.model.ProductModel

@Entity
data class HomeCategoryLocalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val slide: String,
    val title_ru: String,
    val title_tm: String,
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
    fun toUIEntity(products: List<ProductLocalEntity>?): HomeCategory {
        return HomeCategory(
            id = id,
            products = products?.map { it.toUIEntity() }?: emptyList(),
            slide = slide,
            title_tm = title_tm,
            title_ru = title_ru,
            slideType = checkSlideType()
        )
    }
}

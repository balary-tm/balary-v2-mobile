package tm.com.balary.features.home.data.entity.product

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.data.datasource.local.SlideLocalEntity
import tm.com.balary.features.home.domain.model.SlideModel
import tm.com.balary.features.home.domain.model.SlideType

@Serializable
data class DiscountImage(
    val name: String,
    val path: String
) {
    fun toCacheEntity(): SlideLocalEntity {
        return SlideLocalEntity(
            name = name,
            imageUrl = path,
            slideType = "image"
        )
    }
    fun toUIEntity(): SlideModel {
        return SlideModel(
            name = name,
            imageUrl = path,
            slideType = SlideType.IMAGE
        )
    }
}
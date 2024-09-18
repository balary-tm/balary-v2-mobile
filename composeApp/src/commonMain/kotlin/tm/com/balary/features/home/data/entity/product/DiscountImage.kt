package tm.com.balary.features.home.data.entity.product

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.domain.model.SlideModel

@Serializable
data class DiscountImage(
    val name: String,
    val path: String
) {
    fun toUIEntity(): SlideModel {
        return SlideModel(
            name = name,
            imageUrl = path
        )
    }
}
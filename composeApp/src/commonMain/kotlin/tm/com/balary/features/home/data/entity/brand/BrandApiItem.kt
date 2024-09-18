package tm.com.balary.features.home.data.entity.brand

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.domain.model.HomeBrandModel

@Serializable
data class BrandApiItem(
    val icon_sm: String?,
    val id: Int,
    val name: String?
) {
    fun toUIEntity(): HomeBrandModel {
        return HomeBrandModel(
            icon_sm = icon_sm?:"",
            id = id,
            name = name?:""
        )
    }
}
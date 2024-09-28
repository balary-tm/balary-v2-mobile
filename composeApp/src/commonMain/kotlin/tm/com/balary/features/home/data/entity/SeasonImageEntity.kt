package tm.com.balary.features.home.data.entity

import kotlinx.serialization.Serializable
import tm.com.balary.features.home.data.datasource.local.SeasonLocalEntity
import tm.com.balary.features.home.domain.model.SeasonModel

@Serializable
data class SeasonImageEntity(
    val name: String,
    val path: String
) {
    fun toCacheEntity(): SeasonLocalEntity {
        return SeasonLocalEntity(
            name = name,
            path = path
        )
    }
    fun toUIEntity(): SeasonModel {
        return SeasonModel(
            name = name,
            path = path
        )
    }
}
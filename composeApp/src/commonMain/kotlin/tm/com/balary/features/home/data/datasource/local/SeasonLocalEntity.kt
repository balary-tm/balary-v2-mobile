package tm.com.balary.features.home.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import tm.com.balary.features.home.domain.model.SeasonModel

@Entity
data class SeasonLocalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val path: String
) {
    fun toUIEntity(): SeasonModel {
        return SeasonModel(
            name = name,
            path = path
        )
    }
}

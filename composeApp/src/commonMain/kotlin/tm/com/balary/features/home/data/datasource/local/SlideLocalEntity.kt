package tm.com.balary.features.home.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import tm.com.balary.features.home.domain.model.SlideModel
import tm.com.balary.features.home.domain.model.SlideType

@Entity
data class SlideLocalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imageUrl: String,
    val slideType: String
) {
    fun toUIEntity(): SlideModel {
        return SlideModel(
            name = name,
            imageUrl = imageUrl,
            slideType = if(slideType == "video") SlideType.VIDEO else SlideType.IMAGE
        )
    }
}

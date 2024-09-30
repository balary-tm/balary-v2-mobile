package tm.com.balary.features.basket.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class BasketLocalEntity(
    @PrimaryKey
    val id: Int,
    val thumbnail: String,
    val title_tm: String,
    val title_ru: String,
    val description_tm: String,
    val description_ru: String,
    val price: Double,
    val priceWithDiscount: Double,
    val discount: Double,
    val disc_remaining_time: Long? = null,
    val count: Int = 0
) {
    fun total(): Double {
        return priceWithDiscount * count
    }

    fun priceWithoutDiscount(): Double {
        return price * count
    }

    fun discountPrice(): Double {
        val discountAmount =
            if (discount > 0) (price * (discount / 100))
            else 0.0

        return discountAmount * count
    }
}

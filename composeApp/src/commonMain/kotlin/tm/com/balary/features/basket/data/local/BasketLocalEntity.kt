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
        val realPrice =
            if (discount > 0) priceWithDiscount + ((priceWithDiscount * 100) % discount)
            else priceWithDiscount

        return realPrice * count
    }

    fun discountPrice(): Double {
        val realPrice =
            if (discount > 0) (priceWithDiscount * 100) % discount
            else 0.0

        return realPrice * count
    }
}

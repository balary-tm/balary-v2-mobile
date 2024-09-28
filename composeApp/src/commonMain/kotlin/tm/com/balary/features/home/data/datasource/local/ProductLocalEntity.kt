package tm.com.balary.features.home.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import tm.com.balary.features.product.domain.model.ProductModel

class ProductLocalType {
    companion object {
        const val DISCOUNT_PRODUCT = "discount_product"
        const val NEW_PRODUCT = "new_product"
        const val NORMAL_PRODUCT = "new_product"
        const val HOME_CATEGORY_PRODUCT = "home_category_product"
    }
}

@Entity
data class ProductLocalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val blurhash: String,
    val disc_remaining_time: Double,
    val discount: Double,
    val discount_price: Double,
    val image: String,
    val price: Double,
    val title_tm: String,
    val title_ru: String,
    val description_tm: String,
    val description_ru: String,
    val type: String,
    val categoryId: Int,
) {
    fun toUIEntity(): ProductModel {
        return ProductModel(
            blurhash = blurhash,
            disc_remaining_time = disc_remaining_time,
            discount = discount,
            discount_price = discount_price,
            id = id,
            image = image,
            price = price,
            title_ru = title_ru,
            title_tm = title_tm,
            description_ru = description_ru,
            description_tm = description_tm
        )
    }
}
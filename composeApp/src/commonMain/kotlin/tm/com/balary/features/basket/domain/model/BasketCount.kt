package tm.com.balary.features.basket.domain.model

data class BasketCount(
    val totalWithoutDiscount: Double = 0.0,
    val discount: Double = 0.0,
    val total: Double = 0.0
)

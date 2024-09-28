package tm.com.balary.features.basket.presentation.state

import tm.com.balary.features.basket.data.local.BasketLocalEntity
import tm.com.balary.features.basket.domain.model.BasketCount

data class BasketState(
    val products: List<BasketLocalEntity> = emptyList(),
    val calculation: BasketCount = BasketCount()
)

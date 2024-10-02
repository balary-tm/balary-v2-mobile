package tm.com.balary.features.basket.presentation.state

import tm.com.balary.features.basket.data.entity.OrderExtraEntity

data class OrderExtraState(
    val loading: Boolean = true,
    val error: String? = null,
    val extra: OrderExtraEntity? = null
)

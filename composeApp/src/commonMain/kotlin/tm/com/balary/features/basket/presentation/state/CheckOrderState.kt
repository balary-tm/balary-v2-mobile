package tm.com.balary.features.basket.presentation.state

import tm.com.balary.features.basket.data.entity.CheckOrderResponse

data class CheckOrderState(
    val loading: Boolean = false,
    val error: String? = null,
    val result: List<CheckOrderResponse>? = null
)

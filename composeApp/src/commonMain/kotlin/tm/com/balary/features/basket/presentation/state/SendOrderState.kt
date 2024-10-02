package tm.com.balary.features.basket.presentation.state

data class SendOrderState(
    val loading: Boolean = false,
    val error: String? = null
)

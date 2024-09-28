package tm.com.balary.features.product.presentation.state

import tm.com.balary.features.product.data.entity.single.SingleProductResponse

data class SingleProductState(
    val loading: Boolean = true,
    val error: String? = null,
    val product: SingleProductResponse? = null
)

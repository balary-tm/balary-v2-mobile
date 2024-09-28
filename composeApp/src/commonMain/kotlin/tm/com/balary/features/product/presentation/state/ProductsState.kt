package tm.com.balary.features.product.presentation.state

import tm.com.balary.features.product.domain.model.ProductsResult
import kotlin.math.ceil

data class ProductsState(
    val loading: Boolean = true,
    val error: String? = null,
    val products: ProductsResult? = null,
    val hasMore: Boolean = true
)

package tm.com.balary.features.favorite.presentation.state

import tm.com.balary.features.product.domain.model.ProductModel

data class FavoriteState(
    val loading: Boolean = true,
    val error: String? = null,
    val favorites: List<ProductModel>? = null
)

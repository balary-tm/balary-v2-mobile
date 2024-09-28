package tm.com.balary.features.product.domain.model

import tm.com.balary.features.product.data.entity.category.Pagination

data class ProductsResult(
    val pagination: Pagination?,
    var products: List<ProductModel>
)

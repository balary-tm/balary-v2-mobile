package tm.com.balary.features.product.domain.model

data class ProductRequest(
    val page: Int = 1,
    val limit: Int = 20,
    val sort: String? = null,
    val categoryId: String? = null
)

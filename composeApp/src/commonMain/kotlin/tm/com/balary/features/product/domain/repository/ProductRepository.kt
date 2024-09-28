package tm.com.balary.features.product.domain.repository

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.product.data.entity.single.SingleProductResponse
import tm.com.balary.features.product.domain.model.ProductRequest
import tm.com.balary.features.product.domain.model.ProductsResult

interface ProductRepository {
    suspend fun getProducts(body: ProductRequest): Flow<Resource<ProductsResult>>
    suspend fun getProductById(id: String): Flow<Resource<SingleProductResponse>>
}
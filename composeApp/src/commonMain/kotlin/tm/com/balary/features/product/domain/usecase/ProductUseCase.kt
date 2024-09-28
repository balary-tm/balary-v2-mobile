package tm.com.balary.features.product.domain.usecase

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.product.data.entity.single.SingleProductResponse
import tm.com.balary.features.product.domain.model.ProductRequest
import tm.com.balary.features.product.domain.model.ProductsResult
import tm.com.balary.features.product.domain.repository.ProductRepository

class ProductUseCase(
    private val repository: ProductRepository
) {
    suspend fun getProducts(body: ProductRequest): Flow<Resource<ProductsResult>> {
        return repository.getProducts(body)
    }
    suspend fun getProductById(id: String): Flow<Resource<SingleProductResponse>> {
        return repository.getProductById(id)
    }
}
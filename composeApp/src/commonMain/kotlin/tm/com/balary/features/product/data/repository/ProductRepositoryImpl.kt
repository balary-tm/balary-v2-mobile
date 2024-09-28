package tm.com.balary.features.product.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tm.com.balary.core.Constant
import tm.com.balary.core.Resource
import tm.com.balary.features.product.data.entity.category.CategoryProduct
import tm.com.balary.features.product.data.entity.single.SingleProductResponse
import tm.com.balary.features.product.domain.model.ProductRequest
import tm.com.balary.features.product.domain.model.ProductsResult
import tm.com.balary.features.product.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val httpClient: HttpClient
) : ProductRepository {
    override suspend fun getProducts(body: ProductRequest): Flow<Resource<ProductsResult>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.get("${Constant.BASE_URL}/categories/${body.categoryId}/products") {
                url {
                    parameters.append("page", body.page.toString())
                    parameters.append("limit", body.limit.toString())
                    if(body.sort.isNullOrEmpty().not())
                        parameters.append("sort", body.sort.toString())
                }
            }
            if(response.status.value in 200..300) {
                val resBody = response.body<CategoryProduct>()
                val result = ProductsResult(
                    pagination = resBody.pagination,
                    products = resBody.data.map { it.toUIEntity() }
                )
                emit(Resource.Success(result))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    override suspend fun getProductById(id: String): Flow<Resource<SingleProductResponse>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.get("${Constant.BASE_URL}/products/$id")
            if(response.status.value in 200..300) {
                val product = response.body<SingleProductResponse>()
                emit(Resource.Success(product))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }
}
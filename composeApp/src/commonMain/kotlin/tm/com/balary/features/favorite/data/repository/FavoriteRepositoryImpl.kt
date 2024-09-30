package tm.com.balary.features.favorite.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tm.com.balary.core.Constant
import tm.com.balary.core.Resource
import tm.com.balary.features.auth.data.settings.AuthSettings
import tm.com.balary.features.favorite.data.entity.FavoriteProductResponse
import tm.com.balary.features.favorite.data.entity.FavoriteRequest
import tm.com.balary.features.favorite.domain.repository.FavoriteRepository
import tm.com.balary.features.product.domain.model.ProductModel

class FavoriteRepositoryImpl(
    private val httpClient: HttpClient,
    private val authSettings: AuthSettings
) : FavoriteRepository {
    override suspend fun addFavorite(productId: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.post("${Constant.BASE_URL}/products/add-to-favs/${productId}") {
                headers.append("Authorization", "Bearer ${authSettings.getToken()}")
            }
            if(response.status.value in 200..299) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    override suspend fun removeFavorite(productId: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.delete("${Constant.BASE_URL}/products/remove-from-favs/${productId}") {
                headers.append("Authorization", "Bearer ${authSettings.getToken()}")
            }
            if(response.status.value in 200..299) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    override suspend fun getFavorites(data: FavoriteRequest): Flow<Resource<List<ProductModel>>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.get("${Constant.BASE_URL}/products/favorites") {
                headers.append("Authorization", "Bearer ${authSettings.getToken()}")
            }
            if(response.status.value in 200..299) {
                val result = response.body<FavoriteProductResponse>()
                emit(Resource.Success(result.data.map { it.toUIEntity() }))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }
}
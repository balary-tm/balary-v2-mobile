package tm.com.balary.features.favorite.domain.usecase

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.favorite.data.entity.FavoriteRequest
import tm.com.balary.features.favorite.domain.repository.FavoriteRepository
import tm.com.balary.features.product.domain.model.ProductModel

class FavoriteUseCase(
    private val repository: FavoriteRepository
) {
    suspend fun addFavorite(productId: String): Flow<Resource<Boolean>> {
        return repository.addFavorite(productId)
    }
    suspend fun removeFavorite(productId: String): Flow<Resource<Boolean>> {
        return repository.removeFavorite(productId)
    }
    suspend fun getFavorites(data: FavoriteRequest): Flow<Resource<List<ProductModel>>> {
        return repository.getFavorites(data)
    }
}
package tm.com.balary.features.favorite.domain.repository

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.favorite.data.entity.FavoriteRequest
import tm.com.balary.features.product.domain.model.ProductModel

interface FavoriteRepository {
    suspend fun addFavorite(productId: String): Flow<Resource<Boolean>>
    suspend fun removeFavorite(productId: String): Flow<Resource<Boolean>>
    suspend fun getFavorites(data: FavoriteRequest): Flow<Resource<List<ProductModel>>>
}
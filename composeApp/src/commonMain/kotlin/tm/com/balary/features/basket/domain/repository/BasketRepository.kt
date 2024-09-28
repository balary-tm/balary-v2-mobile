package tm.com.balary.features.basket.domain.repository

import tm.com.balary.features.basket.data.local.BasketLocalEntity

interface BasketRepository {
    suspend fun getBasket(): List<BasketLocalEntity>
    suspend fun addToBasket(basketLocalEntity: BasketLocalEntity)
    suspend fun deleteById(id: Int)
    suspend fun deleteAll()
}
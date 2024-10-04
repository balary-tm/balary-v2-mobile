package tm.com.balary.features.basket.domain.repository

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.basket.data.entity.CheckOrderResponse
import tm.com.balary.features.basket.data.entity.OrderExtraEntity
import tm.com.balary.features.basket.data.entity.OrderRequestBody
import tm.com.balary.features.basket.data.local.BasketLocalEntity

interface BasketRepository {
    suspend fun getBasket(): List<BasketLocalEntity>
    suspend fun addToBasket(basketLocalEntity: BasketLocalEntity)
    suspend fun deleteById(id: Int)
    suspend fun deleteAll()
    suspend fun getOrderExtra(): Flow<Resource<OrderExtraEntity>>
    suspend fun sendOrder(data: OrderRequestBody): Flow<Resource<Boolean>>
    suspend fun checkOrder(): Flow<Resource<List<CheckOrderResponse>>>
}
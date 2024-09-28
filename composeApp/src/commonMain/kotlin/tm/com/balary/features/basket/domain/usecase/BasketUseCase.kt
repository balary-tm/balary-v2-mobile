package tm.com.balary.features.basket.domain.usecase

import tm.com.balary.features.basket.data.local.BasketLocalEntity
import tm.com.balary.features.basket.domain.repository.BasketRepository

class BasketUseCase(
    private val repository: BasketRepository
) {
    suspend fun getBasket(): List<BasketLocalEntity> {
        return repository.getBasket()
    }
    suspend fun addToBasket(basketLocalEntity: BasketLocalEntity) {
        when(basketLocalEntity.count) {
            0 -> repository.deleteById(basketLocalEntity.id)
            else -> repository.addToBasket(basketLocalEntity)
        }
    }
    suspend fun deleteById(id: Int) {
        repository.deleteById(id)
    }
    suspend fun deleteAll() {
        repository.deleteAll()
    }
}
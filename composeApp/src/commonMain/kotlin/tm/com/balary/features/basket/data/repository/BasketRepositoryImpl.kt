package tm.com.balary.features.basket.data.repository

import tm.com.balary.database.AppDatabase
import tm.com.balary.features.basket.data.local.BasketLocalEntity
import tm.com.balary.features.basket.domain.repository.BasketRepository

class BasketRepositoryImpl(
    private val db: AppDatabase
): BasketRepository {
    override suspend fun getBasket(): List<BasketLocalEntity> {
        val dao = db.getBasketDao()
        val list = dao.getBasket()
        return list
    }

    override suspend fun addToBasket(basketLocalEntity: BasketLocalEntity) {
        val dao = db.getBasketDao()
        dao.addToBasket(basketLocalEntity)
    }

    override suspend fun deleteById(id: Int) {
        val dao = db.getBasketDao()
        dao.deleteById(id)
    }

    override suspend fun deleteAll() {
        val dao = db.getBasketDao()
        dao.deleteAll()
    }
}
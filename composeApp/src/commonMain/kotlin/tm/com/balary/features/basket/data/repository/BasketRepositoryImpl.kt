package tm.com.balary.features.basket.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tm.com.balary.core.Constant
import tm.com.balary.core.Resource
import tm.com.balary.database.AppDatabase
import tm.com.balary.features.auth.data.settings.AuthSettings
import tm.com.balary.features.basket.data.entity.CheckOrderRequest
import tm.com.balary.features.basket.data.entity.CheckOrderResponse
import tm.com.balary.features.basket.data.entity.OrderExtraEntity
import tm.com.balary.features.basket.data.entity.OrderRequestBody
import tm.com.balary.features.basket.data.entity.Product
import tm.com.balary.features.basket.data.local.BasketLocalEntity
import tm.com.balary.features.basket.domain.repository.BasketRepository

class BasketRepositoryImpl(
    private val httpClient: HttpClient,
    private val db: AppDatabase,
    private val authSettings: AuthSettings
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

    override suspend fun getOrderExtra(): Flow<Resource<OrderExtraEntity>> = flow {
        emit(Resource.Loading())
        try {
            val response = httpClient.get("${Constant.BASE_URL}/orders/extra")
            if(response.status.value in 200..299) {
                val result = response.body<OrderExtraEntity>()
                emit(Resource.Success(result))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    override suspend fun sendOrder(data: OrderRequestBody): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        delay(2000L)
        try {
            val response = httpClient.post("${Constant.BASE_URL}/orders") {
                setBody(data)
                contentType(ContentType.Application.Json)
                headers.append("Authorization","Bearer ${authSettings.getToken()}")
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

    override suspend fun checkOrder(): Flow<Resource<List<CheckOrderResponse>>> = flow {
        emit(Resource.Loading())
        val dao = db.getBasketDao()
        val orders = dao.getBasket()
        val data = CheckOrderRequest(
            products = orders.map {
                Product(
                    cart_discount = it.discount,
                    cart_discount_price = it.priceWithDiscount,
                    cart_price = it.price,
                    quantity = it.count,
                    product_id = it.id
                )
            }
        )
        delay(2000L)
        try {
            val response = httpClient.post("${Constant.BASE_URL}/orders/check") {
                setBody(data)
                contentType(ContentType.Application.Json)
                headers.append("Authorization","Bearer ${authSettings.getToken()}")
            }
            if(response.status.value in 200..299) {
                val result = response.body<List<CheckOrderResponse>>()
                if(result.isNotEmpty()) {
                    result.forEach { res->
                        if(res.out_of_stock) {
                            dao.deleteById(res.product_id)
                        } else {
                            dao.addToBasket(
                                item = orders.find { it.id == res.product_id }?.copy(
                                    priceWithDiscount = res.new_discount_price,
                                    count = res.new_quantity,
                                    discount = res.new_discount,
                                    price = res.new_price
                                )!!
                            )
                        }

                    }
                }
                emit(Resource.Success(result))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }
}
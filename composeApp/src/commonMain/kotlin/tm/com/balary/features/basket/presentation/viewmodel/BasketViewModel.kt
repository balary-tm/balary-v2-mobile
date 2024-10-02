package tm.com.balary.features.basket.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tm.com.balary.core.Resource
import tm.com.balary.database.AppDatabase
import tm.com.balary.features.auth.data.entity.request.prettyPhone
import tm.com.balary.features.basket.data.entity.Address
import tm.com.balary.features.basket.data.entity.OrderDay
import tm.com.balary.features.basket.data.entity.OrderLine
import tm.com.balary.features.basket.data.entity.OrderRequestBody
import tm.com.balary.features.basket.data.local.BasketLocalEntity
import tm.com.balary.features.basket.domain.model.BasketCount
import tm.com.balary.features.basket.domain.usecase.BasketUseCase
import tm.com.balary.features.basket.presentation.state.BasketState
import tm.com.balary.features.basket.presentation.state.OrderExtraState
import tm.com.balary.features.basket.presentation.state.OrderFormState
import tm.com.balary.features.basket.presentation.state.SendOrderState

enum class OrderDayType {
    TODAY,
    TOMORROW
}

class BasketViewModel(
    private val useCase: BasketUseCase,
) : ViewModel() {
    private val _basketState = MutableStateFlow(BasketState())
    val basketState = _basketState.asStateFlow()

    private val _orderExtraState = MutableStateFlow(OrderExtraState())
    val orderExtraState = _orderExtraState.asStateFlow()

    val selectedTimes = mutableStateOf<List<OrderDay>>(emptyList())
    val selectedDay = mutableStateOf(OrderDayType.TODAY)
    val selectedInterval = mutableStateOf(0)

    val deliveryPrice = mutableStateOf(0.0)

    val selectedPaymentType = mutableStateOf("cash")
    val selectedDeliveryType = mutableStateOf("standard") // express, self

    private val _sendOrderState = MutableStateFlow(SendOrderState())
    val sendOrderState = _sendOrderState.asStateFlow()

    fun setDeliveryPrice(price: Double) {
        deliveryPrice.value = price
    }

    fun setSelectedDeliveryType(type: String) {
        selectedDeliveryType.value = type
    }

    fun setSelectedPaymentType(type: String) {
        selectedPaymentType.value = type
    }

    fun setSelectedInterval(id: Int) {
        selectedInterval.value = id
    }

    fun setSelectedDay(dayType: OrderDayType) {
        selectedDay.value = dayType
    }

    fun setSelectedTimes(time: List<OrderDay>) {
        selectedTimes.value = time
    }

    fun initOrderExtra() {
        if(_orderExtraState.value.extra==null) {
            getOrderExtra()
        }
    }

    fun makeOrder(orderFormState: OrderFormState, onError: (String?) -> Unit, onSuccess: ()-> Unit) {
        viewModelScope.launch {
            useCase.sendOrder(
                data = OrderRequestBody(
                    note = orderFormState.note,
                    phone = orderFormState.phoneNumber.prettyPhone(),
                    delivery_interval_id = selectedInterval.value,
                    delivery_day = when(selectedDay.value) {
                        OrderDayType.TODAY -> "today"
                        OrderDayType.TOMORROW -> "tomorrow"
                    },
                    payment_type = selectedPaymentType.value,
                    delivery_type = selectedDeliveryType.value,
                    order_lines = _basketState.value.products.map { p->
                        OrderLine(
                            quantity = p.count,
                            variant_id = p.id
                        )
                    },
                    address = Address(
                        room = orderFormState.room,
                        floor = try {
                            orderFormState.floor.toFloat()
                        } catch (_: Exception) {0f},
                        street = orderFormState.street,
                        house = orderFormState.house,
                        district = orderFormState.district,
                        latitude = 37.054,
                        longitude = 56.043
                    )
                )
            ).onEach { result->
                when(result) {
                    is Resource.Error -> {
                        onError(result.message)
                        _sendOrderState.value = _sendOrderState.value.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _sendOrderState.value = _sendOrderState.value.copy(
                            loading = true,
                            error = result.message
                        )
                    }
                    is Resource.Success -> {
                        deleteAll()
                        onSuccess()
                        _sendOrderState.value = _sendOrderState.value.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun getOrderExtra() {
        viewModelScope.launch {
            useCase.getOrderExtra().onEach { result->
                when(result) {
                    is Resource.Error -> {
                        _orderExtraState.value = _orderExtraState.value.copy(
                            loading = false,
                            error = result.message,
                            extra = result.data
                        )
                    }
                    is Resource.Loading -> {
                        _orderExtraState.value = _orderExtraState.value.copy(
                            loading = true,
                            error = result.message,
                            extra = result.data
                        )
                    }
                    is Resource.Success -> {
                        result.data?.let { extra->
                            deliveryPrice.value = extra.delivery_price?:0.0
                            extra.intervals?.let { intervals ->
                                if(intervals.today.isNullOrEmpty().not()) {
                                    selectedTimes.value = intervals.today!!
                                } else if(intervals.tomorrow.isNullOrEmpty().not()) {
                                    selectedDay.value = OrderDayType.TOMORROW
                                    selectedTimes.value = intervals.tomorrow!!
                                }
                            }
                        }
                        _orderExtraState.value = _orderExtraState.value.copy(
                            loading = false,
                            error = result.message,
                            extra = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun getBasket() {
        viewModelScope.launch {
            val products = useCase.getBasket()
            val calculation = BasketCount(
                totalWithoutDiscount = products.sumOf { it.priceWithoutDiscount() },
                total = products.sumOf { it.total() },
                discount = products.sumOf { it.discountPrice() },
            )
            _basketState.value = _basketState.value.copy(
                products = products,
                calculation = calculation
            )
        }
    }

    fun addBasket(item: BasketLocalEntity) {
        viewModelScope.launch {
            useCase.addToBasket(item)
            getBasket()
        }
    }

    fun deleteById(id: Int) {
        viewModelScope.launch {
            useCase.deleteById(id)
            getBasket()
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            useCase.deleteAll()
            getBasket()
        }
    }
}
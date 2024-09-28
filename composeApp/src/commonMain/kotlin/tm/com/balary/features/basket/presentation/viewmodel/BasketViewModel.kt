package tm.com.balary.features.basket.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tm.com.balary.features.basket.data.local.BasketLocalEntity
import tm.com.balary.features.basket.domain.model.BasketCount
import tm.com.balary.features.basket.domain.usecase.BasketUseCase
import tm.com.balary.features.basket.presentation.state.BasketState

class BasketViewModel(
    private val useCase: BasketUseCase
) : ViewModel() {
    private val _basketState = MutableStateFlow(BasketState())
    val basketState = _basketState.asStateFlow()

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
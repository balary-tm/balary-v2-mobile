package tm.com.balary.features.product.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tm.com.balary.core.Resource
import tm.com.balary.features.product.domain.model.ProductModel
import tm.com.balary.features.product.domain.model.ProductRequest
import tm.com.balary.features.product.domain.model.ProductsResult
import tm.com.balary.features.product.domain.usecase.ProductUseCase
import tm.com.balary.features.product.presentation.state.ProductsState
import tm.com.balary.features.product.presentation.state.SingleProductState

class ProductViewModel(
    private val useCase: ProductUseCase
) : ViewModel() {

    /**
     * This section code to get list of products by pagination, filter, sort. Use it with only [androidx.compose.foundation.lazy.LazyColumn]
     */
    private val _productsState = MutableStateFlow(ProductsState())
    val productsState = _productsState.asStateFlow()

    val body = mutableStateOf(ProductRequest())

    fun restartProducts() {
        _productsState.value = ProductsState()
        body.value = body.value.copy(
            page = 1
        )
        getProducts()
    }

    fun initProducts(categoryId: String?) {
        if(_productsState.value.products==null) {
            body.value = body.value.copy(
                categoryId = categoryId
            )
            getProducts()
        }
    }

    fun nextPageProducts() {
        if(_productsState.value.loading.not() && _productsState.value.hasMore) {
            body.value = body.value.copy(
                page = body.value.page.plus(1)
            )
            getProducts()
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            useCase.getProducts(body.value).onEach { result->
                when (result) {
                    is Resource.Error -> {
                        body.value = body.value.copy(
                            page = body.value.page.minus(1)
                        )
                        _productsState.value = _productsState.value.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _productsState.value = _productsState.value.copy(
                            loading = true
                        )
                    }
                    is Resource.Success -> {
                        val old: List<ProductModel> = _productsState.value.products?.products?: emptyList()
                        val newProducts = result.data?.products?: emptyList()
                        _productsState.value = _productsState.value.copy(
                            loading = false,
                            error = result.message,
                            hasMore = newProducts.isNotEmpty(),
                            products = ProductsResult(
                                products = old.plus(newProducts),
                                pagination = result.data?.pagination
                            )
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    /**
     * Product list getting section ending here
     */


    /**
     * Single product data getting code to return [tm.com.balary.features.product.data.entity.single.SingleProductResponse]
     */
    private val _singleProductState = MutableStateFlow(SingleProductState())
    val singleProductState = _singleProductState.asStateFlow()

    /**
     * This function checking if cached data exist do not get it again from api network request
     * If cached data not found call [getSingleProduct] function
     * [getSingleProduct] fetch data from api through [ProductUseCase]
     * return type is [tm.com.balary.features.product.data.entity.single.SingleProductResponse] as Flow
     */
    fun initSingleProduct(id: String) {
        if(_singleProductState.value.product==null) {
            getSingleProduct(id)
        }
    }

    fun getSingleProduct(id: String) {
        viewModelScope.launch {
            useCase.getProductById(id).onEach { result->
                when(result) {
                    is Resource.Error -> {
                        _singleProductState.value = _singleProductState.value.copy(
                            loading = false,
                            error = result.message,
                            product = result.data
                        )
                    }
                    is Resource.Loading -> {
                        _singleProductState.value = _singleProductState.value.copy(
                            loading = true,
                            error = result.message,
                            product = result.data
                        )
                    }
                    is Resource.Success -> {
                        _singleProductState.value = _singleProductState.value.copy(
                            loading = false,
                            error = result.message,
                            product = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    /**
     * Single product getting code end
     */
}
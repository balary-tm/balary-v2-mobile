package tm.com.balary.features.favorite.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tm.com.balary.core.Resource
import tm.com.balary.features.favorite.data.entity.FavoriteRequest
import tm.com.balary.features.favorite.domain.usecase.FavoriteUseCase
import tm.com.balary.features.favorite.presentation.state.FavoriteState
import tm.com.balary.features.product.domain.model.ProductModel

class FavoriteViewModel(
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {
    private val _favoriteState = MutableStateFlow(FavoriteState())
    val favoriteState = _favoriteState.asStateFlow()

    fun initFavorites() {
        if(_favoriteState.value.favorites.isNullOrEmpty()) {
            getFavorites()
        }
    }

    fun getFavorites() {
        viewModelScope.launch {
            favoriteUseCase.getFavorites(
                data = FavoriteRequest()
            ).onEach { result->
                when(result) {
                    is Resource.Error -> {
                        _favoriteState.value = _favoriteState.value.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _favoriteState.value = _favoriteState.value.copy(
                            loading = true,
                            error = result.message
                        )
                    }
                    is Resource.Success -> {
                        _favoriteState.value = _favoriteState.value.copy(
                            loading = false,
                            error = result.message,
                            favorites = result.data?.map { p->
                                p.copy(isFavorite = true)
                            }
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun addFavorite(
        productId: String,
        onLoading: () -> Unit,
        onError: (String?) -> Unit,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            favoriteUseCase.addFavorite(productId).onEach { result->
                when(result) {
                    is Resource.Error -> {
                        onError(result.message)
                    }
                    is Resource.Loading -> {
                        onLoading()
                    }
                    is Resource.Success -> {
                        getFavorites()
                        onSuccess()
                    }
                }
            }.launchIn(this)
        }
    }

    fun deleteFromState(productId: String) {
        _favoriteState.value = _favoriteState.value.copy(
            favorites = _favoriteState.value.favorites?.filter { it.id.toString() != productId }
        )
    }

    fun deleteFavorite(
        productId: String,
        onLoading: () -> Unit,
        onError: (String?) -> Unit,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            favoriteUseCase.removeFavorite(productId).onEach { result->
                when(result) {
                    is Resource.Error -> {
                        onError(result.message)
                    }
                    is Resource.Loading -> {
                        onLoading()
                    }
                    is Resource.Success -> {
                        deleteFromState(productId)
                        onSuccess()
                    }
                }
            }.launchIn(this)
        }
    }
}
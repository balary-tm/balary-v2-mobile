package tm.com.balary.features.category.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tm.com.balary.core.Resource
import tm.com.balary.features.category.domain.usecase.CategoryUseCase
import tm.com.balary.features.category.presentation.state.ChildCategoryState
import tm.com.balary.features.category.presentation.state.ParentCategoryState

class CategoryViewModel(
    private val useCase: CategoryUseCase
) : ViewModel() {
    private val _parentCategoryState = MutableStateFlow(ParentCategoryState())
    val parentCategoryState = _parentCategoryState.asStateFlow()

    private val _childCategoryState = MutableStateFlow(ChildCategoryState())
    val childCategoryState = _childCategoryState.asStateFlow()

    fun initChildCategories(id: String?) {
        if(_childCategoryState.value.category==null) {
            getChildCategories(id)
        }
    }

    fun getChildCategories(id: String?) {
        if(id.isNullOrEmpty().not()) {
            id?.let {
                viewModelScope.launch {
                    useCase.getChildCategories(id).onEach { result ->
                        when (result) {
                            is Resource.Error -> {
                                _childCategoryState.value = _childCategoryState.value.copy(
                                    loading = false,
                                    error = result.message,
                                    category = result.data
                                )
                            }
                            is Resource.Loading -> {
                                _childCategoryState.value = _childCategoryState.value.copy(
                                    loading = true,
                                    error = result.message,
                                    category = result.data
                                )
                            }
                            is Resource.Success -> {
                                _childCategoryState.value = _childCategoryState.value.copy(
                                    loading = false,
                                    error = result.message,
                                    category = result.data
                                )
                            }
                        }
                    }.launchIn(this)
                }
            }
        }
    }

    fun initCategories() {
        if(_parentCategoryState.value.categories.isNullOrEmpty()) {
            getParentCategories()
        }
    }

    fun getParentCategories() {
        viewModelScope.launch {
            useCase.getCategories().onEach { result->
                when(result) {
                    is Resource.Error -> {
                        _parentCategoryState.value = _parentCategoryState.value.copy(
                            loading = false,
                            error = result.message,
                            categories = result.data
                        )
                    }
                    is Resource.Loading -> {
                        _parentCategoryState.value = _parentCategoryState.value.copy(
                            loading = true,
                            error = result.message,
                            categories = result.data
                        )
                    }
                    is Resource.Success -> {
                        _parentCategoryState.value = _parentCategoryState.value.copy(
                            loading = false,
                            error = result.message,
                            categories = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}
package tm.com.balary.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tm.com.balary.common.getCurrentVersion
import tm.com.balary.common.getDevice
import tm.com.balary.core.Resource
import tm.com.balary.features.home.domain.usecase.HomeUseCase
import tm.com.balary.features.home.presentation.state.BannerState
import tm.com.balary.features.home.presentation.state.BrandState
import tm.com.balary.features.home.presentation.state.CheckVersionState
import tm.com.balary.features.home.presentation.state.HomeCategoryState
import tm.com.balary.features.home.presentation.state.HomeProductState
import tm.com.balary.features.home.presentation.state.SeasonImageState

class HomeViewModel(
    private val useCase: HomeUseCase
) : ViewModel() {
    private val _bannerState = MutableStateFlow(BannerState())
    val bannerState = _bannerState.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _seasonImage = MutableStateFlow(SeasonImageState())
    val seasonImage = _seasonImage.asStateFlow()

    private val _homeProducts = MutableStateFlow(HomeProductState())
    val homeProducts = _homeProducts.asStateFlow()

    private val _categoryState = MutableStateFlow(HomeCategoryState())
    val categoryState = _categoryState.asStateFlow()

    private val _brandState = MutableStateFlow(BrandState())
    val brandState = _brandState.asStateFlow()

    private val _versionCheckState = MutableStateFlow(CheckVersionState())
    val versionCheckState = _versionCheckState.asStateFlow()

    fun initBanners() {
        if(_bannerState.value.banners.isNullOrEmpty()) {
            getBanners()
        }
    }

    fun getBanners() {
        scope.launch {
            useCase.getBanners().onEach {
                when(it) {
                    is Resource.Error -> {
                        _bannerState.value = _bannerState.value.copy(
                            loading = false,
                            error = it.message,
                            banners = it.data
                        )
                    }
                    is Resource.Loading -> {
                        _bannerState.value = _bannerState.value.copy(
                            loading = true,
                            error = it.message,
                            banners = it.data
                        )
                    }
                    is Resource.Success -> {
                        _bannerState.value = _bannerState.value.copy(
                            loading = false,
                            error = it.message,
                            banners = it.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun initSeasonImage() {
        if(_seasonImage.value.seasonImage==null) {
            getSeasonImage()
        }
    }

    fun getSeasonImage() {
        scope.launch {
            useCase.getSeasonImage().onEach {
                when(it) {
                    is Resource.Error -> {
                        _seasonImage.value = _seasonImage.value.copy(
                            loading = false,
                            error = it.message,
                            seasonImage = it.data
                        )
                    }
                    is Resource.Loading -> {
                        _seasonImage.value = _seasonImage.value.copy(
                            loading = true,
                            error = it.message,
                            seasonImage = it.data
                        )
                    }
                    is Resource.Success -> {
                        _seasonImage.value = _seasonImage.value.copy(
                            loading = false,
                            error = it.message,
                            seasonImage = it.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun initProducts() {
        if(_homeProducts.value.productModel==null) {
            getHomeProducts()
        }
    }

    fun getHomeProducts() {
        scope.launch {
            useCase.getHomeProducts().onEach { result->
                when(result) {
                    is Resource.Error -> {
                        _homeProducts.value = _homeProducts.value.copy(
                            loading = false,
                            error = result.message,
                            productModel = result.data
                        )
                    }
                    is Resource.Loading -> {
                        _homeProducts.value = _homeProducts.value.copy(
                            loading = true,
                            error = result.message,
                            productModel = result.data
                        )
                    }
                    is Resource.Success -> {
                        _homeProducts.value = _homeProducts.value.copy(
                            loading = false,
                            error = result.message,
                            productModel = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun initCategories() {
        if(_categoryState.value.category.isNullOrEmpty()) {
            getCategories()
        }
    }

    fun getCategories() {
        scope.launch {
            useCase.getCategories().onEach { result->
                when(result) {
                    is Resource.Error -> {
                        _categoryState.value = _categoryState.value.copy(
                            loading = false,
                            error = result.message,
                            category = result.data
                        )
                    }
                    is Resource.Loading -> {
                        _categoryState.value = _categoryState.value.copy(
                            loading = true,
                            error = result.message,
                            category = result.data
                        )
                    }
                    is Resource.Success -> {
                        _categoryState.value = _categoryState.value.copy(
                            loading = false,
                            error = result.message,
                            category = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun getBrands() {
        scope.launch {
            useCase.getBrands().onEach { result->
                when(result) {
                    is Resource.Error -> {
                        _brandState.value = _brandState.value.copy(
                            loading = false,
                            error = result.message,
                            brand = result.data
                        )
                    }
                    is Resource.Loading -> {
                        _brandState.value = _brandState.value.copy(
                            loading = true,
                            error = result.message,
                            brand = result.data
                        )
                    }
                    is Resource.Success -> {
                        _brandState.value = _brandState.value.copy(
                            loading = false,
                            error = result.message,
                            brand = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun initBrands() {
        if(_brandState.value.brand.isNullOrEmpty()) {
            getBrands()
        }
    }

    fun initVersion() {
        if(_versionCheckState.value.versions.isNullOrEmpty()) {
            checkVersion()
        }
    }

    fun checkVersion() {
        scope.launch {
            useCase.checkVersion(
                device = getDevice(),
                currentVersion = getCurrentVersion()
            ).onEach { result->
                when(result) {
                    is Resource.Error -> {
                        _versionCheckState.value =_versionCheckState.value.copy(
                            loading = false,
                            error = result.message,
                            versions = result.data
                        )
                    }
                    is Resource.Loading -> {
                        _versionCheckState.value =_versionCheckState.value.copy(
                            loading = true,
                            error = result.message,
                            versions = result.data
                        )
                    }
                    is Resource.Success -> {
                        _versionCheckState.value =_versionCheckState.value.copy(
                            loading = false,
                            error = result.message,
                            versions = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}